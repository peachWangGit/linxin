package com.dhlg.module.system.sysFile.controller;


import com.dhlg.module.system.sysFile.service.ISysFileService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.utils.common.exception.ParamIsNullException;
import io.swagger.annotations.ApiOperation;
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
 * @since 2020-05-01
 */
@RestController
@RequestMapping("/api/system/sysFile")
public class SysFileController {
    @Autowired
    ISysFileService doservice;

    @ApiOperation("上传图片")
    @PostMapping(value = "/uploadFile")
    public Result uploadFile(@RequestParam("file") MultipartFile file){

        if(StringUtils.isBlank(file)){
            throw new ParamIsNullException();
        }
        return doservice.uploadFile(file);
    }

    @ApiOperation("删除图片")
    @PostMapping(value = "/deleteFile/{id}")
    public Result deleteFile(@PathVariable String id){

        if(StringUtils.isBlank(id)){
            throw new ParamIsNullException();
        }
        return doservice.deleteFile(id);
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

