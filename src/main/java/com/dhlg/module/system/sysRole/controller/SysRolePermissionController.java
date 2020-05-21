package com.dhlg.module.system.sysRole.controller;


import com.dhlg.module.system.sysRole.entity.SysRolePermission;
import com.dhlg.module.system.sysRole.service.ISysRolePermissionService;
import com.dhlg.utils.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since 2020-02-06
 */
@Api(value = "sysRole：角色管理权限接口", tags = "系统管理")
@RestController
@RequestMapping("/api/system/sysRolePermission")
@CrossOrigin
public class SysRolePermissionController {

    @Autowired
    ISysRolePermissionService sysRolePermissionService;

    @RequiresPermissions("rolePermission:save")
    @ApiOperation("权限配置-批量保存")
    @PutMapping("/batchsave/{roleId}")
    public Result permissionConfigBatchSave(@PathVariable String roleId, @RequestBody List<SysRolePermission> sysRolePermissions) {
        boolean b = sysRolePermissionService.batchSave(roleId,sysRolePermissions);
        return new Result("200", b, "SUCCESS");
    }
}

