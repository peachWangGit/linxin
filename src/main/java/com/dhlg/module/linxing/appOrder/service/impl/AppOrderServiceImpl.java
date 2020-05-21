package com.dhlg.module.linxing.appOrder.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.linxing.appOrder.entity.AppOrder;
import com.dhlg.module.linxing.appOrder.dao.AppOrderMapper;
import com.dhlg.module.linxing.appOrder.service.IAppOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.module.system.sysUser.entity.SysUser;
import com.dhlg.utils.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@Service
public class AppOrderServiceImpl extends ServiceImpl<AppOrderMapper, AppOrder> implements IAppOrderService {

    @Autowired
    AppOrderMapper doMapper;

    @Override
    public Result customSaveOrUpdate(AppOrder appOrder) {
        //判断新增还是修改
        if (StringUtils.isBlank(appOrder.getId())) {

            return new Result("200","","未查询到该订单");
        }

        //新增
        appOrder.setOrderStatus(3);

        if(!updateById(appOrder)){
            return new Result("500","","发货失败");
        }
//        if (!save(appOrder)){
//            return new Result("500","", Dictionaries.SAVE_FAILED);
//        }
        return new Result("200","","发货成功");
    }

    @Override
    public Result delete(String id) {
        if (!removeById(id)){
            return new Result("500","", Dictionaries.DELETE_FAILED);
        }
        return new Result("200","",Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result deleteBatch(List<String> ids) {
        if (!removeByIds(ids)){
            return new Result("500","", Dictionaries.DELETE_FAILED);
        }
        return new Result("200","",Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result queryByCondition(Parameter parameter) {
        parameter.setDefault();
        IPage<AppOrder> dataList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(dataList);
    }

    @Override
    public Result listFieldQuery(Map<String, Object> params) {
        SysUser user= GetLoginUser.getCurrentUser();
        if (StringUtils.isBlank(user)){
            return new Result("500","", "您还未的登录");
        }
        params.put("departmentId",Integer.parseInt(user.getDepartmentId()));
        if (!user.getDepartmentId().equals("0")){

        }
        Long number = Long.valueOf(String.valueOf(params.getOrDefault("number", 1)));
        Long size = Long.valueOf(String.valueOf(params.getOrDefault("size", 10)));

        IPage<AppOrder> dataList = doMapper.listFieldQuery(new Page(number, size), params);
        return new Result("200", dataList);
    }
}
