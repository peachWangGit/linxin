package com.dhlg.module.system.sysRole.controller;


import com.dhlg.module.system.sysMenu.entity.SysMenu;
import com.dhlg.module.system.sysRole.entity.SysRole;
import com.dhlg.module.system.sysRole.service.ISysRoleService;
import com.dhlg.module.system.sysRole.service.impl.SysRolePermissionServiceImpl;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.utils.common.exception.ParamIsNullException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import com.dhlg.utils.common.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author xu
 * @since 2020-02-04
 */
@Api(value = "sysRole：角色管理接口", tags = "系统管理")
@RestController
@RequestMapping("/api/system/sysRole")
@CrossOrigin
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private SysRolePermissionServiceImpl sysRolePermissionService;


    @ApiOperation("查询角色")
    @PostMapping ("/querybycondition")
    public Result queryByCondition(@RequestBody Parameter parameter) {
        if (StringUtils.isBlank(parameter)) {
            throw new ParamIsNullException();
        }
        return sysRoleService.queryByCondition(parameter);
    }

    @ApiOperation("获取所有角色")
    @PostMapping ("/getList")
    public Result getRoleList() {

        List<SysRole> sysMenuList = sysRoleService.getRoleList();
        return new Result(sysMenuList);
    }

    @RequiresPermissions("sysRole:save")
    @ApiOperation("更新或新增角色")
    @PostMapping ("/saveorupdate")
    public Result saveorupdate(@RequestBody SysRole sysRole) {
        if (StringUtils.isBlank(sysRole)) {
            throw new ParamIsNullException();
        }
        return sysRoleService.saveorupdate(sysRole);
    }

    @RequiresPermissions("sysRole:delete")
    @ApiOperation("删除角色")
    @PostMapping ("/deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            throw new ParamIsNullException();
        }
        return sysRoleService.deleteById(id);
    }

    @RequiresPermissions("sysRole:batchDelete")
    @ApiOperation("批量删除角色")
    @PostMapping ("/batchDelete")
    public Result batchDelete(@RequestBody Set<String> ids) {

        return sysRoleService.batchDelete(ids);
    }

    @RequiresPermissions("sysRole:updateStatus")
    @ApiOperation("批量更新角色状态")
    @PutMapping ("/batchUpdate")
    public Result batchUpdate(@RequestBody List<SysRole> sysRoleList) {
        return sysRoleService.batchUpdate(sysRoleList);
    }

    @ApiOperation("权限配置-按钮和菜单组合")
    @GetMapping("/getButMenu")
    public Result getButMenu() {
        List<SysMenu> sysMenus = sysRoleService.getButMenu();
        return new Result("200", sysMenus, "SUCCESS");
    }

    @ApiOperation("权限配置-通过id获取按钮权限数据")
    @GetMapping("/getButByRoleId/{id}")
    public Result getButByRoleId(@PathVariable String id) {
        List<String> permissionButton = sysRolePermissionService.getButByRoleId(id);
        return new Result("200", permissionButton, "SUCCESS");
    }

}

