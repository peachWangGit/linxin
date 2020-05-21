package com.dhlg.module.system.sysMenu.controller;


import com.dhlg.module.system.sysMenu.entity.SysMenu;
import com.dhlg.module.system.sysMenu.service.ISysMenuService;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.utils.common.exception.ParamIsNullException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since 2020-01-07
 */
@Api(value = "sysMenu：菜单配置接口", tags = "系统管理")
@RestController
@RequestMapping("/api/system/sysMenu")
@CrossOrigin
public class SysMenuController {
    @Autowired
    private ISysMenuService sysMenuService;



    @ApiOperation("获取所有菜单节点")
    @RequestMapping(value = "/getnodelist", method = RequestMethod.POST)
    public Result getNodeList() {
        Result result = new Result();
        HashMap<String, Object> params = new HashMap<>();
        params.put("status", null);// 查出全部菜单项
        List<SysMenu> sysMenuList = sysMenuService.getNodeList(params);

        if (!CollectionUtils.isEmpty(sysMenuList)) {
            result.setBody(sysMenuList);
        } else {
            result.setCode("400");
            result.setMessage("查询菜单节点出错");
        }
        return result;
    }

    @RequiresPermissions("sysMenu:save")
    @ApiOperation("保存菜单节点")
    @PostMapping(value = "/savenode")
    public Result saveNode(@RequestBody SysMenu sysMenu) {
        if (StringUtils.isBlank(sysMenu)) {
//            throw new ParameterIsNullException();
            throw new ParamIsNullException();
        }
        return sysMenuService.saveNode(sysMenu);
    }


    @RequiresPermissions("sysMenu:delete")
    @ApiOperation("删除菜单节点")
    @DeleteMapping(value = "/deletenode/{id}")
    public Result deleteNode(@PathVariable String id) {
        Result result = new Result();
        boolean flag = sysMenuService.deleteNode(id);
        if (!flag) {
            result.setCode("400");
            result.setMessage("删除菜单节点出错");
        }
        return result;
    }
}

