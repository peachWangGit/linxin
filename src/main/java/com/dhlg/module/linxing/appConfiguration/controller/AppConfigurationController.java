package com.dhlg.module.linxing.appConfiguration.controller;


import com.dhlg.module.linxing.appConfiguration.entity.AppConfiguration;
import com.dhlg.module.linxing.appConfiguration.service.IAppConfigurationService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.utils.common.exception.ParamIsNullException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since 2020-05-06
 */
@RestController
@RequestMapping("/api/linxing/appConfiguration")
public class AppConfigurationController {

    @Autowired
    IAppConfigurationService doService;

    @ApiOperation("获取全部数据")
    @PostMapping("/getAllData")
    public Result getAllData() {
        return  doService.getAllData();
    }

    @ApiOperation("更改数据")
    @PostMapping("/changeData")
    public Result changeData(@RequestBody  List<AppConfiguration> lists) {
        return  doService.changeData(lists);
    }
}

