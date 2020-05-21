package com.dhlg.module.linxing.appMenu.controller;


import com.dhlg.module.linxing.appMenu.entity.AppMenu;
import com.dhlg.module.linxing.appMenu.service.IAppMenuService;
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
 * @since 2020-05-02
 */
@RestController
@CrossOrigin
@Api(value = "appCarousel：菜单管理",tags = "小程序管理")
@RequestMapping("/api/linxing/appMenu")
public class AppMenuController {

    @Autowired
    IAppMenuService doservice;


    @ApiOperation("保存或者更新")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody AppMenu appMenu) {
        if(StringUtils.isBlank(appMenu)){
            throw new ParamIsNullException();
        }
        return  doservice.customSaveOrUpdate(appMenu);
    }


    @ApiOperation("删除")
    @DeleteMapping("/deleteById/{id}")
    public Result delete(@PathVariable String id) {
        boolean delete = doservice.delete(id);
        return new Result("200", delete, "SUCCESS");
    }

    @ApiOperation("批量删除")
    @PostMapping("/batchdelete")
    public Result batchDelete(@RequestBody List<String> ids) {
        boolean b = doservice.deleteBatch(ids);
        return new Result("200", b, "SUCCESS");
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

