package com.dhlg.module.linxing.appCooperation.controller;


import com.dhlg.module.linxing.appCooperation.entity.AppCooperation;
import com.dhlg.module.linxing.appCooperation.service.IAppCooperationService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.utils.common.exception.ParamIsNullException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@RestController
@RequestMapping("/api/linxing/appCooperation")
@CrossOrigin
public class AppCooperationController {

    @Autowired
    IAppCooperationService doservice;

    @ApiOperation("保存或者更新")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody AppCooperation appCooperation) {
        if(StringUtils.isBlank(appCooperation)){
            throw new ParamIsNullException();
        }
        return  doservice.customSaveOrUpdate(appCooperation);
    }


    @ApiOperation("删除")
    @DeleteMapping("/deleteById/{id}")
    public Result delete(@PathVariable String id) {

        return doservice.delete(id);
    }

    @ApiOperation("批量删除")
    @PostMapping("/batchdelete")
    public Result batchDelete(@RequestBody List<String> ids) {
        return  doservice.deleteBatch(ids);
    }

    @ApiOperation("根据条件分页获取按钮数据")
    @PostMapping("/querybycondition")
    public Result queryByCondition(@RequestBody Parameter parameter) {
        if(StringUtils.isBlank(parameter)){
            throw new ParamIsNullException();
        }
        return  doservice.queryByCondition(parameter);
    }
}

