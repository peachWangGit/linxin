package com.dhlg.module.linxing.appCarousel.controller;


import com.dhlg.module.linxing.appCarousel.entity.AppCarousel;
import com.dhlg.module.linxing.appCarousel.service.IAppCarouselService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.utils.common.exception.ParamIsNullException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since 2020-04-30
 */
@RestController
@CrossOrigin
@Api(value = "appCarousel：app轮播图",tags = "小程序管理")
@RequestMapping("/api/linxing/appCarousel")
public class AppCarouselController {

    @Autowired
    IAppCarouselService doservice;


    @ApiOperation("保存或者更新")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody AppCarousel appCarousel) {
        if(StringUtils.isBlank(appCarousel)){
            throw new ParamIsNullException();
        }
        return  doservice.customSaveOrUpdate(appCarousel);
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

