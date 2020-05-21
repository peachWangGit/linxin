package com.dhlg.module.system.sysLoginLog.controller;


import com.dhlg.module.system.sysLoginLog.service.ISysLoginLogService;
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
 * @since 2020-02-22
 */
@RestController
@RequestMapping("/api/system/sysLoginLog")
@CrossOrigin
public class SysLoginLogController {

    @Autowired
    ISysLoginLogService loginLogService;

    @ApiOperation("根据条件分页获取日志数据")
    @PostMapping("/query")
    public Result query(@RequestBody Parameter parameter){
        if(StringUtils.isBlank(parameter)){
            throw new ParamIsNullException();
        }
        return loginLogService.queryByCondition(parameter);
    }


    @ApiOperation("批量删除日志")
    @PostMapping("/batchdelete")
    public Result batchDeleteLoginLog(@RequestBody List<String> ids) {

        return loginLogService.batchDeleteByIds(ids);

    }
}

