package com.dhlg.module.system.sysRole.service;

import com.dhlg.module.system.sysRole.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since 2020-02-06
 */
public interface ISysRolePermissionService extends IService<SysRolePermission> {

    boolean batchSave(String roleId, List<SysRolePermission> sysRolePermissions);
}
