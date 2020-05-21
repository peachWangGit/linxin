package com.dhlg.module.system.sysUser.controller;


import com.dhlg.module.system.sysUser.entity.SysUser;
import com.dhlg.module.system.sysUser.service.ISysUserService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.utils.common.exception.ParamIsNullException;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since 2020-01-07
 */
@RestController
@RequestMapping("/api/system/sysUser")
@CrossOrigin
public class SysUserController {

    @Autowired
    ISysUserService doService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody String body, HttpServletRequest request){
        if(StringUtils.isBlank(body)){
            throw new ParamIsNullException();
        }
        return doService.login(body, request);
    }

    @ApiOperation("退出系统")
    @PostMapping("/logout")
    public void logout(){
        SecurityUtils.getSubject().logout();
    }
    @ApiOperation("当前在其他地方已登录，您已被踢出！")
    @PostMapping("/kickout")
    public Result kickout() {
        return new Result("401", null, "当前用户在其他地方已登录，您已被踢出！");
    }

    @ApiOperation("获取所有管理员")
    @PostMapping ("/getList")
    public Result getList() {

        List<SysUser> sysUserList = doService.getList();
        return new Result(sysUserList);
    }

    @RequiresPermissions("sysUser:save")
    @ApiOperation("更新或新增管理员")
    @PostMapping ("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysUser sysUser) {
        if (StringUtils.isBlank(sysUser)) {
            throw new ParamIsNullException();
        }
        return doService.saveorupdate(sysUser);
    }

    @ApiOperation("查询管理员")
    @PostMapping ("/queryByCondition")
    public Result queryByCondition(@RequestBody Parameter parameter) {
        if (StringUtils.isBlank(parameter)) {
            throw new ParamIsNullException();
        }
        return doService.queryByCondition(parameter);
    }

    @RequiresPermissions("sysUser:batchDelete")
    @ApiOperation("批量删除管理员")
    @PostMapping ("/batchDelete")
    public Result batchDelete(@RequestBody Set<String> ids) {

        return doService.batchDelete(ids);
    }

    @RequiresPermissions("sysUser:deleteID")
    @ApiOperation("删除管理员")
    @DeleteMapping ("/deleteById/{id}")
    public Result deleteById(@PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            throw new ParamIsNullException();
        }
        return doService.deleteById(id);
    }

    @RequiresPermissions("sysUser:resetPassword")
    @ApiOperation("重置密码")
    @PostMapping ("/resetPassword/{id}")
    public Result resetPassword(@PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            throw new ParamIsNullException();
        }
        return doService.resetPassword(id);
    }

    @ApiOperation("根据id找到用户")
    @PostMapping ("/queryById/{id}")
    public Result queryById(@PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            throw new ParamIsNullException();
        }
        return doService.queryById(id);
    }

    @ApiOperation("根据id找到用户头像")
    @PostMapping ("/queryHeadPortraitById/{id}")
    public Result queryHeadPortraitById(@PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            throw new ParamIsNullException();
        }
        return doService.queryHeadPortraitById(id);
    }

    @ApiOperation("更新用户信息")
    @PostMapping ("/updateUserInfo")
    public Result userUpdateUserInfo(@RequestBody SysUser sysUser){
        if (StringUtils.isBlank(sysUser)) {
            throw new ParamIsNullException();
        }
        return doService.userUpdateUserInfo(sysUser);
    }

    @ApiOperation("更新用户密码")
    @PostMapping ("/updatePassword")
    public Result updatePassword(@RequestBody Parameter parameter){
        if (StringUtils.isBlank(parameter)) {
            throw new ParamIsNullException();
        }
        return doService.updatePassword(parameter);
    }


}

