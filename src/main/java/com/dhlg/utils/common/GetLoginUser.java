package com.dhlg.utils.common;

import com.dhlg.module.system.sysUser.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 获取当前登陆的信息
 * Created by Administrator on 2019-04-01.
 */
public class GetLoginUser {
    public static SysUser getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        if (StringUtils.isBlank(subject)) return null;
        Object principal = subject.getPrincipal();
        if (StringUtils.isBlank(principal)) return null;
        return (SysUser) principal;
    }


    public static String getCurrentUserId() {
        SysUser currentUser = getCurrentUser();
        if(StringUtils.isBlank(currentUser))return "";
        return currentUser.getId();
    }


//    public static String getCurrentUserOrgId() {
//        SysUser currentUser = getCurrentUser();
//        if(StringUtils.isBlank(currentUser))return "";
//        return currentUser.getOrgId();
//    }
//
//    public static String getCurrentUserDepId() {
//        SysUser currentUser = getCurrentUser();
//        if(StringUtils.isBlank(currentUser))return "";
//        return currentUser.getDepId();
//    }
}
