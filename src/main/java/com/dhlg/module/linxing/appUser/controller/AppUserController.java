package com.dhlg.module.linxing.appUser.controller;

import com.dhlg.module.linxing.appUser.entity.AppUser;
import com.dhlg.module.linxing.appUser.service.IAppUserService;
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
 * @since
 */
@RestController
@RequestMapping("/api/linxing/appUser")
@CrossOrigin
public class AppUserController {

        @Autowired
        IAppUserService doService;


        @ApiOperation("更改积分")
        @PostMapping("/changeJIfen")
        public Result changeJIfen(@RequestBody AppUser appUser) {
                if(StringUtils.isBlank(appUser)){
                        throw new ParamIsNullException();
                }
                return  doService.changeJIfen(appUser);
        }

        @ApiOperation("保存或者更新")
        @PostMapping("/saveOrUpdate")
        public Result saveOrUpdate(@RequestBody AppUser appUser) {
                if(StringUtils.isBlank(appUser)){
                        throw new ParamIsNullException();
                }
                return  doService.customSaveOrUpdate(appUser);
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

        @ApiOperation("获取数量信息")
        @PostMapping("/getCount")
        public Result getCount() {
                return  doService.getCount();
        }
}

