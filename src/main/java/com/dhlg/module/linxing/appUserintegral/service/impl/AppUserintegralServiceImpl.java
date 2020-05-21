package com.dhlg.module.linxing.appUserintegral.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.linxing.appUserintegral.entity.AppUserintegral;
import com.dhlg.module.linxing.appUserintegral.dao.AppUserintegralMapper;
import com.dhlg.module.linxing.appUserintegral.service.IAppUserintegralService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AppUserintegralServiceImpl extends ServiceImpl<AppUserintegralMapper, AppUserintegral> implements IAppUserintegralService {

    @Autowired
    AppUserintegralMapper doMapper;

    @Override
    public Result customSaveOrUpdate(AppUserintegral appUserintegral) {
        //判断新增还是修改
        if (!StringUtils.isBlank(appUserintegral.getId())) {
            //更新
            if(!updateById(appUserintegral)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }
            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }

        if (!save(appUserintegral)){
            return new Result("500","", Dictionaries.SAVE_FAILED);
        }
        return new Result("200","",Dictionaries.SAVE_SUCCESS);
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
        IPage<AppUserintegral> buttonConfigList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(buttonConfigList);
    }

    @Override
    public Result listFieldQuery(Map<String, Object> params) {
        Long number = Long.valueOf(String.valueOf(params.getOrDefault("number", 1)));
        Long size = Long.valueOf(String.valueOf(params.getOrDefault("size", 10)));
        IPage<AppUserintegral> appUserintegralIPage = doMapper.listFieldQuery(new Page(number, size), params);
        return new Result("200", appUserintegralIPage);
    }
}
