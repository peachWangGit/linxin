package com.dhlg.module.system.sysButton.controller;


import com.dhlg.module.system.sysButton.entity.SysButtonConfig;
import com.dhlg.module.system.sysButton.service.ISysButtonConfigService;
import com.dhlg.module.system.sysButton.service.impl.SysButtonConfigServiceImpl;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.utils.common.exception.ParamIsNullException;
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
 * @since 2020-02-07
 */
@RestController
@RequestMapping("/api/system/sysbuttonconfig")
@Api(value = "SysButtonConfig：按钮", tags = "系统管理")
@CrossOrigin
public class SysButtonConfigController {


    @Autowired
    ISysButtonConfigService service;

    @RequiresPermissions("buttonConfig:save")
    @ApiOperation("保存或者更新")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysButtonConfig sysButtonConfig) {
        if(StringUtils.isBlank(sysButtonConfig)){
            throw new ParamIsNullException();
        }

        return  service.customSaveOrUpdate(sysButtonConfig);
    }

    @RequiresPermissions("buttonConfig:deleteID")
    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        boolean delete = service.delete(id);
        return new Result("200", delete, "SUCCESS");
    }
    @RequiresPermissions("buttonConfig:batchDelete")
    @ApiOperation("批量删除")
    @PostMapping("/batchdelete")
    public Result batchDelete(@RequestBody List<String> ids) {
        boolean b = service.deleteBatch(ids);
        return new Result("200", b, "SUCCESS");
    }

    @ApiOperation("根据条件分页获取按钮数据")
    @PostMapping("/querybycondition")
    public Result queryByCondition(@RequestBody Parameter parameter) {
        if(StringUtils.isBlank(parameter)){
            throw new ParamIsNullException();
        }
        return  service.queryByCondition(parameter);
    }


}

