package com.dhlg.utils.shiro.realm;

import com.dhlg.module.system.sysButton.entity.SysButton;
import com.dhlg.module.system.sysButton.service.impl.SysButtonServiceImpl;
import com.dhlg.module.system.sysRole.service.impl.SysRoleServiceImpl;
import com.dhlg.module.system.sysUser.entity.SysUser;
import com.dhlg.module.system.sysUser.service.impl.SysUserServiceImpl;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.utils.shiro.utils.LoggerUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户验证与授权 realm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private SysUserServiceImpl userService;

    @Autowired
    @Lazy
    private SysRoleServiceImpl roleService;

    @Autowired
    @Lazy
    private SysButtonServiceImpl buttonService;

    /**
     * 用户调用改方法验证权限时进入此方法： SecurityUtils.getSubject();
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LoggerUtils.debug(UserRealm.class,"加载权限--->doGetAuthorizationInfo()");

        //获取用户
        SysUser user = (SysUser) getAvailablePrincipal(principals);

        Set<String> roleList = new HashSet<>();
        Set<String> menuPermissionSet = new HashSet<>();

        List<SysButton> buttonList = null;
        //从数据库查询该用户的角色和权限数据
        if("admin".equals(user.getUserName())){
            //超级管理员，具有所有权限
            buttonList = buttonService.findAllButtonUrl();

        }else{
            //不是超级管理员
            roleList= roleService.findRoles(user.getRoleIds());
            buttonList= buttonService.findButtonUrl(user.getRoleIds());
        }
        if (!StringUtils.isBlank(buttonList)){
            for(SysButton sysMenu:buttonList){
                if (!StringUtils.isBlank(sysMenu.getButtonUrl())){
                    menuPermissionSet.add(sysMenu.getButtonUrl());
                }
            }
        }

        //设置该用户的角色和权限数据
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //角色访问，
        authorizationInfo.setRoles(roleList);

        /*
         *  该权限必须给接口处添加@RequiresPermissions("xxx")，但是考虑到这样做只能做到增删改查等分批的接口，
         *  ，每个接口都要@RequiresPermissions太过于麻烦，未来还会有其他的各种按钮，
         *  从而必须增加一个表来随时更新这些按钮的权限，这样根本无法做到真正的简约。
         *  本框架在直接在shiroConfig的拦截中，找到按钮的url，处理了这些后台接口。
         *  所以此处为学习使用，此框架没有用到。
         */

        authorizationInfo.setStringPermissions(menuPermissionSet);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {

        //判断是否为用户登入
        UsernamePasswordToken token = null;
        //instanceof检验token是否为UsernamePasswordToken的实例
        if(authenticationToken instanceof UsernamePasswordToken){
            token = (UsernamePasswordToken) authenticationToken;
        }else{
            return null;
        }
        //获取用户的输入的账号.
        String login_user = (String)token.getPrincipal();

        //根据用户名获取用户对象
        SysUser sysUser = userService.findByName(login_user);

        if(sysUser == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), this.getName());
    }
    @Override
    public boolean supports(AuthenticationToken var1){
        return var1 instanceof UsernamePasswordToken;
    }

}