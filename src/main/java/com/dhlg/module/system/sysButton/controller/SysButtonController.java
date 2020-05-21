package com.dhlg.module.system.sysButton.controller;


import com.dhlg.module.system.sysButton.entity.SysButton;
import com.dhlg.module.system.sysButton.service.ISysButtonService;
import com.dhlg.utils.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since 2020-02-01
 */
@RestController
@CrossOrigin
@Api(value = "sysbutton：按钮操作接口",tags = "系统管理")
@RequestMapping("/api/system/sysbutton")
public class SysButtonController {

    @Autowired
    private ISysButtonService sysButtonService;

    @RequiresPermissions("sysButton:save")
    @ApiOperation("新增")
    @RequestMapping("/insert")
    public Result insert(@RequestBody SysButton sysButton) {
        return  sysButtonService.insert(sysButton);
    }

    @RequiresPermissions("sysButton:delete")
    @ApiOperation("删除")
    @DeleteMapping("/deletebyid/{id}")
    public Result deleteById(@PathVariable String id) {
        try {
            boolean b = sysButtonService.deleteById(id);
            return new Result("200", b, "SUCCESS");
        } catch (Exception e) {
            return new Result("500", e.getMessage(), "ERROR");
        }
    }

}

