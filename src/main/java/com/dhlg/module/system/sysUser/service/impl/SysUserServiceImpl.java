package com.dhlg.module.system.sysUser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.linxing.appCooperation.entity.AppCooperation;
import com.dhlg.module.linxing.appCooperation.service.impl.AppCooperationServiceImpl;
import com.dhlg.module.system.sysButton.entity.SysButton;
import com.dhlg.module.system.sysButton.service.impl.SysButtonServiceImpl;
import com.dhlg.module.system.sysMenu.entity.SysMenu;
import com.dhlg.module.system.sysMenu.service.impl.SysMenuServiceImpl;;
import com.dhlg.module.system.sysUser.entity.SysUser;
import com.dhlg.module.system.sysUser.dao.SysUserMapper;
import com.dhlg.module.system.sysUser.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.common.*;
import com.dhlg.utils.common.exception.ParamIsNullException;
import com.dhlg.utils.common.exception.UncheckedException;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-01-07
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


    @Autowired(required = false)
    SysUserMapper sysUserMapper;

    @Autowired(required = false)
    SysMenuServiceImpl sysMenuService ;

    @Autowired(required = false)
    SysButtonServiceImpl buttonService;

    @Autowired(required = false)
    AppCooperationServiceImpl appCooperationService;

    @Autowired
    VerificationProperty verificationProperty;

    @Value("${sysuser.headPortraitSrc}")
    private String headPortraitSrc;

    final static String PASSWORD = "123456";
    @Override
    public Result login(String body, HttpServletRequest request) {
        Map<String, Object> dataMap = new HashMap<>();
        JSONObject jsonObject = JSONObject.fromObject(body);
        Object username = jsonObject.get( "userName");
        Object password = jsonObject.get( "passWord");
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new ParamIsNullException();
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username.toString(), password.toString()));

            //shrio验证登入，410账户密码错误，411锁定
        } catch (LockedAccountException e) {
        //throw new LockedAccountException(" 该账号已被锁定！");
            return new Result("411", request.getRequestURL(),"该账号已被锁定！");
        } catch (AuthenticationException e) {
    //throw new UnknownAccountException(" 账号或密码错误！");
            return new Result("410", request.getRequestURL(), "账号或密码错误！");
        }

        subject = SecurityUtils.getSubject();
        SysUser user =  GetLoginUser.getCurrentUser();


            //获取树状menu
        List<SysMenu> sysMenus=sysMenuService.findMenu(username.toString());
        dataMap.put("menuList",sysMenus);
        sysMenus= InitTree.getRootNodes(sysMenus);
        dataMap.put("menuData",sysMenus);
        dataMap.put("token",  subject.getSession().getId());
        int badgeNum=appCooperationService.getBadgeNum();
        dataMap.put("badgeNum",  badgeNum);

        if (!StringUtils.isBlank(user)){
            dataMap.put("userId", user.getId());
            dataMap.put("user", user);
            Set<String> buttonUrlList= new HashSet<>();
            List<SysButton> buttonList;
            if (user.getLoginUser().equals("admin")){
                //这里不允许重复，所以用set代替list
                buttonList= buttonService.findAllButtonUrl();
            }else {
                buttonList= buttonService.findButtonUrl(user.getRoleIds());
            }
            for(SysButton sysMenu:buttonList){
                if (!StringUtils.isBlank(sysMenu.getButtonUrl())){
                    buttonUrlList.add(sysMenu.getButtonUrl());
                }
            }
            dataMap.put("buttonUrlList", buttonUrlList);
        }

        return new Result("200",dataMap,"登入成功");
    }

    @Override
    public List<SysUser> getList() {

        return super.list();
    }

    @Override
    public Result saveorupdate(SysUser sysUser) {
        Result result = new Result();
        //判断账户名是否为空
        if (StringUtils.isBlank(sysUser.getLoginUser())){
            throw new UncheckedException(Dictionaries.LOGIN_USER_IS_NULL,Dictionaries.LOGIN_USER_IS_NULL,Dictionaries.LOGIN_USER_IS_NULL);
        }
        //判断更新还是新增
        if (StringUtils.isBlank(sysUser.getId())){
            sysUser.setId(StringUtils.uuid());
            //新增,1.判断账号名是否重名

            boolean isRepeat=verificationProperty.lockAndVerify(sysUser, SysUser.LOGIN_USER,sysUser.getId());
            if (isRepeat){
                throw new UncheckedException(Dictionaries.NAME_REPETITION,Dictionaries.NAME_REPETITION,Dictionaries.NAME_REPETITION);
            }
            sysUser.setCreateTime(DateUtils.getCurrentDate());
            sysUser.setCreateUser(GetLoginUser.getCurrentUserId());
            //初始化密码
            sysUser.setPassword(PASSWORD);
            result.setCode("200");
            result.setBody(Dictionaries.SAVE_SUCCESS);

            //2.新增操作
            if (!save(sysUser)){
                throw new UncheckedException(Dictionaries.SAVE_FAILED,Dictionaries.SAVE_FAILED,Dictionaries.SAVE_FAILED);
            }
        }else {
            //更新,1.判断账号名是否重名
            boolean isRepeat=verificationProperty.lockAndVerify(sysUser,SysUser.LOGIN_USER,sysUser.getId());
            if (isRepeat){
                throw new UncheckedException(Dictionaries.NAME_REPETITION,Dictionaries.NAME_REPETITION,Dictionaries.NAME_REPETITION);
            }
            Subject currentUser = SecurityUtils.getSubject();

            sysUser.setUpdateTime(DateUtils.getCurrentDate());
            sysUser.setUpdateUser(GetLoginUser.getCurrentUserId());
            //2.更新操作
            if (!updateById(sysUser)){
                throw new UncheckedException(Dictionaries.UPDATE_FAILED,Dictionaries.UPDATE_FAILED,Dictionaries.UPDATE_FAILED);
            }
            result.setCode("200");
            result.setBody(Dictionaries.UPDATE_SUCCESS);
        }
        return result;
    }

    @Override
    public Result queryByCondition(Parameter parameter) {
        //如果前端传递的分页数据为空，则设置为默认
        parameter.setDefault();
        IPage<SysUser> projectTeamIPage = sysUserMapper.queryByCondition(parameter.getPage(), parameter);

        return new Result(projectTeamIPage);
    }

    @Override
    public Result batchDelete(Set<String> ids) {
        if (super.removeByIds(ids)){
            return new Result("200",Dictionaries.DELETE_SUCCESS,Dictionaries.DELETE_SUCCESS);
        }
        return new Result("400",Dictionaries.DELETE_FAILED,Dictionaries.DELETE_FAILED);
    }

    @Override
    public Result deleteById(String id) {
        Result result = new Result();
        result.setBody(Dictionaries.DELETE_SUCCESS);
        if (!removeById(id)){
            result.setBody(Dictionaries.DELETE_FAILED);
        }
        return result;
    }

    @Override
    public Result resetPassword(String id) {
        Result result = new Result();
        result.setBody(Dictionaries.RESET_PASSWORD_SUCCESS);
        SysUser sysUser = getById(id);
        if (null == sysUser) {
            result.setBody(Dictionaries.RESET_PASSWORD_FAILED);
            return result;
        }
        sysUser.setPassword(PASSWORD);
        if (!updateById(sysUser)) result.setBody(Dictionaries.RESET_PASSWORD_FAILED);
        return result;
    }

    @Override
    public Result queryById(String id) {

        return new Result(super.getById(id));
    }

    @Override
    public Result queryHeadPortraitById(String id) {
        try {
            Result result = new Result();
            SysUser sysUser = super.getById(id);
            //Base64解密图片
            if (StringUtils.isBlank(sysUser.getHeadPortrait())){
                sysUser.setHeadPortrait(ImgUtils.dealImgOfByte(headPortraitSrc));
            }
            byte[] bytes = new BASE64Decoder().decodeBuffer(new String(sysUser.getHeadPortrait()));
            String baseString = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
            result.setBody(baseString);
            //Base64解析图片流并返回字符串

            return result;
        } catch (IOException e) {
            return new Result(e.getMessage(), e.getMessage(), e.getMessage());
        }
    }

    @Override
    public Result userUpdateUserInfo(SysUser sysUser) {
        Result result = new Result();

        //对头像流进行截取
        if (StringUtils.isNotBlank(sysUser.getHeadPortraitSrc())) {
            //对图片src转换成字节
            byte[] bytes = ImgUtils.dealImgOfByte(sysUser.getHeadPortraitSrc());
            sysUser.setHeadPortrait(bytes);
        }
        boolean flag = super.updateById(sysUser);
        if (!flag) {
            return new Result("500",Dictionaries.UPDATE_FAILED);
        }
        return new Result("200",Dictionaries.UPDATE_SUCCESS);
    }

    @Override
    public Result updatePassword(Parameter parameter) {
        Result result = new Result();
        result.setBody(Dictionaries.UPDATE_PASSWORD_SUCCESS);
        //1.得到前端具体参数(id、原密码、新密码)
        Map<String, String> condition = parameter.getCondition();
        String id = condition.get("id");
        String password = condition.get("password");
        String newPassword = condition.get("newPassword");

        //2.比较原密码是否正确
        SysUser sysUser = super.getById(id);
        String code = verifyOldPasswordByName(sysUser, password).getCode();
        if ("400".equals(code)) {
            result.buildFailedResult(Dictionaries.OLD_PASSWORD_ERROR, Dictionaries.OLD_PASSWORD_ERROR,
                    Dictionaries.OLD_PASSWORD_ERROR);
            return result;
        }

        //3.修改密码
        SysUser sysObj = super.getById(id);
        //用户名
        sysUser.setId(id);
        sysUser.setUserName(sysObj.getUserName());
        sysUser.setLoginUser(sysObj.getLoginUser());
        //新密码进行shiro加密
        sysUser.setPassword(newPassword);
        boolean flag = super.updateById(sysUser);
        if (!flag) {
            result.buildFailedResult(Dictionaries.UPDATE_PASSWORD_FAILED, Dictionaries.UPDATE_PASSWORD_FAILED,
                    Dictionaries.UPDATE_PASSWORD_FAILED);
        }
        return result;
    }


    public SysUser findByName(String loginUser) {
        //创建条件构造器
        QueryWrapper<SysUser> queryWrapper_user = new QueryWrapper<>();
        //手机和登入名都可以登入
        queryWrapper_user.eq("login_user", loginUser);
        return super.getOne(queryWrapper_user);
    }

    //验证输入的密码是否正确
    public Result verifyOldPasswordByName(SysUser user, String newPassword) {

        //把用户输入的密码加密
//        String correctPassword = new SimpleHash("md5",
//                oldPassword,
//                ByteSource.Util.bytes(user.getCredentialsSalt()),
//                2
//        ).toHex();

        //把用户输入的密码加密后和数据库的密码比对
        if (newPassword.equals(user.getPassword())) {
            return new Result("200", null, "密码输入正确");
        }
        return new Result("400", null, "密码输入错误");
    }


}
