package com.dhlg.module.linxing.appArticle.controller;


import com.dhlg.module.linxing.appArticle.entity.AppArticle;
import com.dhlg.module.linxing.appArticle.service.IAppArticleService;
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
 * @since 2020-05-02
 */
@RestController
@RequestMapping("/api/linxing/appArticle")
@CrossOrigin
public class AppArticleController {

    @Autowired
    IAppArticleService doservice;

    @ApiOperation("保存或者更新")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody AppArticle appArticle) {
        if(StringUtils.isBlank(appArticle)){
            throw new ParamIsNullException();
        }
        return  doservice.customSaveOrUpdate(appArticle);
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

