package com.dhlg.module.system.sysUser.service;

import com.dhlg.module.system.sysUser.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since 2020-01-07
 */
public interface ISysUserService extends IService<SysUser> {

    Result login(String body, HttpServletRequest request);

    List<SysUser> getList();

    Result saveorupdate(SysUser sysUser);

    Result queryByCondition(Parameter parameter);

    Result batchDelete(Set<String> ids);

    Result deleteById(String id);

    Result resetPassword(String id);

    Result queryById(String id);

    Result queryHeadPortraitById(String id);

    Result userUpdateUserInfo(SysUser sysUser);

    Result updatePassword(Parameter parameter);
}
