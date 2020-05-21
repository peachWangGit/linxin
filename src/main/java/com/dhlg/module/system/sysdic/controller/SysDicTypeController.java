package com.dhlg.module.system.sysdic.controller;


import com.dhlg.module.system.sysdic.entity.Option;
import com.dhlg.module.system.sysdic.entity.SysDicType;
import com.dhlg.module.system.sysdic.service.impl.SysDicTypeServiceImpl;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/system/sysdictype")
@Api(value = "sysdictype：字典类型操作接口", tags = "系统管理")
@CrossOrigin
public class SysDicTypeController {

    @Autowired
    SysDicTypeServiceImpl sysDicTypeService;


    @RequiresPermissions("dicType:save")
    @ApiOperation("保存0)或者更新(1)")
    @PostMapping("/save")
    public Result save(@RequestBody SysDicType sysDicType) {
        int i = sysDicTypeService.saveOrUpdateM(sysDicType);
        return new Result("200", i, "SUCCESS");
    }


    @ApiOperation("字典类型分页带参搜索")
    @PostMapping("/querybypage")
    public Result queryByPage(@RequestBody Parameter parameter) {

        return  sysDicTypeService.pageDicType(parameter);
    }

    @RequiresPermissions("dicType:deleteID")
    @ApiOperation("删除字典类型")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        boolean delete = sysDicTypeService.delete(id);
        return new Result("200", delete, "SUCCESS");
    }

    @RequiresPermissions("dicType:bacthDelete")
    @ApiOperation("删除字典类型")
    @PostMapping("/bacthdelete")
    public Result bacthDelete(@RequestBody List<String> ids) {
        boolean deleteBatch = sysDicTypeService.deleteBatch(ids);
        return new Result("200", deleteBatch, "SUCCESS");
    }

    @ApiOperation("获取字典类型")
    @GetMapping("/gettype/{dicType}")
    public Result getType(@PathVariable String dicType ){
        List<Option> options = sysDicTypeService.getType(dicType);
        return new Result("200", options, "SUCCESS");
    }
}

