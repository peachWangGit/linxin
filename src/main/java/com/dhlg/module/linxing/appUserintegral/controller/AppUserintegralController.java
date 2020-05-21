package com.dhlg.module.linxing.appUserintegral.controller;

import com.dhlg.module.linxing.appUserintegral.entity.AppUserintegral;
import com.dhlg.module.linxing.appUserintegral.service.IAppUserintegralService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.utils.common.exception.ParamIsNullException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since
 */
@RestController
@RequestMapping("/api/linxing/appUserintegral")
@CrossOrigin
public class AppUserintegralController {

        @Autowired
        IAppUserintegralService doService;


        @ApiOperation("保存或者更新")
        @PostMapping("/saveOrUpdate")
        public Result saveOrUpdate(@RequestBody AppUserintegral appUserintegral) {
                if(StringUtils.isBlank(appUserintegral)){
                        throw new ParamIsNullException();
                }
                return  doService.customSaveOrUpdate(appUserintegral);
        }


        @ApiOperation("删除")
        @DeleteMapping("/deleteById/{id}")
        public Result delete(@PathVariable String id) {

                return doService.delete(id);
        }

        @ApiOperation("批量删除")
        @PostMapping("/batchdelete")
        public Result batchDelete(@RequestBody List<String> ids) {

                return doService.deleteBatch(ids);
        }

        @ApiOperation("根据条件分页获取按钮数据")
        @PostMapping("/querybycondition")
        public Result queryByCondition(@RequestBody Parameter parameter) {
                if(StringUtils.isBlank(parameter)){
                        throw new ParamIsNullException();
                }
                return  doService.queryByCondition(parameter);
        }

        @ApiOperation("列表字段查询")
        @PostMapping("/listFieldQuery")
        public Result listFieldQuery(@RequestBody Map<String, Object> params) {
                return  doService.listFieldQuery(params);
        }
}

