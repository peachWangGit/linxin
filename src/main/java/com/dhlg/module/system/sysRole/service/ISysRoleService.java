package com.dhlg.module.system.sysRole.service;

import com.dhlg.module.system.sysMenu.entity.SysMenu;
import com.dhlg.module.system.sysRole.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author xu
 * @since 2020-02-04
 */
public interface ISysRoleService extends IService<SysRole> {

    List<SysRole> getRoleList();

    Result saveorupdate(SysRole sysRole);

    Result deleteById(String id);

    Result queryByCondition(Parameter parameter);

    Result batchDelete(Set<String> ids);

    Result batchUpdate(List<SysRole> sysRoleList);

    List<SysMenu> getButMenu();


}
