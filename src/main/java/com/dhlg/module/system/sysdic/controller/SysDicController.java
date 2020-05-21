package com.dhlg.module.system.sysdic.controller;


import com.dhlg.module.system.sysdic.entity.SysDic;
import com.dhlg.module.system.sysdic.service.impl.SysDicServiceImpl;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.exception.OperatorException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/system/sysdic")
@Api(value = "sysdic：字典标签操作接口", tags = "系统管理")
@CrossOrigin
public class SysDicController {

    @Autowired
    SysDicServiceImpl sysDicService;

    @ApiOperation("获取字典数据列表")
    @GetMapping("/querybypageanddictypeid/{dicTypeId}")
    public Result queryByPageAndDicTypeID(@PathVariable String dicTypeId) throws IOException, OperatorException {
        return sysDicService.listDic(dicTypeId);
    }

    @ApiOperation("获取字典数据列表")
    @GetMapping("/queryAllDic")
    public Result queryAllDic(){
        return sysDicService.queryAllDic();
    }

    @RequiresPermissions("sysdic:batchDelete")
    @ApiOperation("删除字典数据")
    @PostMapping("/batchdelete")
    public Result batchDelete(@RequestBody List<String> ids) {
        boolean b = sysDicService.deleteBatch(ids);
        return new Result("200", b, "SUCCESS");
    }

    @RequiresPermissions("sysdic:deleteID")
    @ApiOperation("删除字典数据")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        boolean b = sysDicService.delete(id);
        return new Result("200", b, "SUCCESS");
    }
    @RequiresPermissions("sysdic:save")
    @ApiOperation("保存(0)或者更新(1)或者字典值重复(-1) ")
    @PostMapping("/save")
    public Result save(@RequestBody SysDic sysDic) {
        int i = sysDicService.saveOrUpdateM(sysDic);
        return new Result("200", i, "SUCCESS");
    }
}

