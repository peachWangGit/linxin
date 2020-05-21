package com.dhlg.module.linxing.appCard.controller;


import com.dhlg.module.linxing.appCard.entity.AppCard;
import com.dhlg.module.linxing.appCard.service.IAppCardService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.utils.common.exception.ParamIsNullException;
import io.swagger.annotations.Api;
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
 * @since 2020-05-03
 */
@RestController
@CrossOrigin
@Api(value = "appCarousel：乐享卡管理",tags = "小程序管理")
@RequestMapping("/api/linxing/appCard")
public class AppCardController {

    @Autowired
    IAppCardService doservice;

    @ApiOperation("保存或者更新")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody AppCard appCard) {
        if(StringUtils.isBlank(appCard)){
            throw new ParamIsNullException();
        }
        return  doservice.customSaveOrUpdate(appCard);
    }


    @ApiOperation("删除")
    @DeleteMapping("/deleteById/{id}")
    public Result delete(@PathVariable String id) {
        return doservice.delete(id);
    }

    @ApiOperation("批量删除")
    @PostMapping("/batchdelete")
    public Result batchDelete(@RequestBody List<String> ids) {
        return doservice.deleteBatch(ids);
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

