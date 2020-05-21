package com.dhlg.module.system.sysRole.dao;

import com.dhlg.module.system.sysRole.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xu
 * @since 2020-02-06
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    List<String> getPermissionMenu(String id);
}
