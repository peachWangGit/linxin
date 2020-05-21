package com.dhlg.module.linxing.appUser.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.linxing.appUser.entity.AppUser;
import com.dhlg.module.linxing.appUser.dao.AppUserMapper;
import com.dhlg.module.linxing.appUser.service.IAppUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.module.linxing.appUserintegral.entity.AppUserintegral;
import com.dhlg.module.linxing.appUserintegral.service.impl.AppUserintegralServiceImpl;
import com.dhlg.module.system.sysUser.entity.SysUser;
import com.dhlg.utils.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService {

    @Autowired
    AppUserMapper doMapper;

    @Autowired
    AppUserintegralServiceImpl userintegralService;

    @Override
    public Result customSaveOrUpdate(AppUser appUser) {
        //判断新增还是修改
        if (!StringUtils.isBlank(appUser.getId())) {
            //修改
            if(!updateById(appUser)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }
            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }
        appUser.setIntegral(0);
        if (!save(appUser)){
            return new Result("500","", Dictionaries.SAVE_FAILED);
        }
        return new Result("200","",Dictionaries.SAVE_SUCCESS);
    }

    @Override
    public Result delete(String id) {
        if (!removeById(id)){
            return new Result("500","", Dictionaries.DELETE_FAILED);
        }
        return new Result("200","",Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result deleteBatch(List<String> ids) {
        if (!removeByIds(ids)){
            return new Result("500","", Dictionaries.DELETE_FAILED);
        }
        return new Result("200","",Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result queryByCondition(Parameter parameter) {
        parameter.setDefault();
        IPage<AppUser> buttonConfigList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(buttonConfigList);
    }

    @Override
    @Transactional
    public Result changeJIfen(AppUser appUser) {
//        appUser.setIntegral(appUser.getIntegral()*100);
        if(!updateById(appUser)){
            return new Result("500","", Dictionaries.UPDATE_FAILED);
        }
        AppUserintegral userintegral = appUser.getUserintegral();
        if (userintegral.getStatus()==3){
            userintegral.setUseTime(DateUtils.getCurrentDate());
        }
        if (userintegral.getStatus()==2){
            userintegral.setTransactionTime(DateUtils.getCurrentDate());
            userintegral.setUnfreezeTime(DateUtils.getCurrentDate());
        }

//        userintegral.setAmount(userintegral.getAmount()*100);
        userintegralService.save(userintegral);
        return new Result("200","",Dictionaries.UPDATE_SUCCESS);
    }

    @Override
    public Result getCount() {
        SysUser user= GetLoginUser.getCurrentUser();
        if (StringUtils.isBlank(user)){
            return new Result("500","", "您还未的登录");
        }
        Map<String, Object> params= new HashMap<>();
        params.put("departmentId",Integer.parseInt(user.getDepartmentId()));
        int userCount=super.count();
        int productCount=doMapper.getProductCount(params);
        int orderCount=doMapper.getOrderCount(params);
        int productAlert=doMapper.getProductAlert(params);

        Map<String, Integer> dataMap = new HashMap<>();
        dataMap.put("userCount",userCount);
        dataMap.put("productCount",productCount);
        dataMap.put("orderCount",orderCount);
        dataMap.put("productAlert",productAlert);
        return new Result("200",dataMap, "");
    }
}
