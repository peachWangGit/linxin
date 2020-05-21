package com.dhlg.module.linxing.appHotelreservation.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.linxing.appHotelreservation.entity.AppHotelreservation;
import com.dhlg.module.linxing.appHotelreservation.dao.AppHotelreservationMapper;
import com.dhlg.module.linxing.appHotelreservation.service.IAppHotelreservationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.common.Dictionaries;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class AppHotelreservationServiceImpl extends ServiceImpl<AppHotelreservationMapper, AppHotelreservation> implements IAppHotelreservationService {

    @Autowired
    AppHotelreservationMapper doMapper;

    @Override
    public Result customSaveOrUpdate(AppHotelreservation appHotelreservation) {
        //判断新增还是修改
        if (!StringUtils.isBlank(appHotelreservation.getId())) {

            //更新
            if(!updateById(appHotelreservation)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }
            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }
        String curTime = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
        String id = curTime + appHotelreservation.getHotelId();
        appHotelreservation.setId(id);
        if (!save(appHotelreservation)){
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
        IPage<AppHotelreservation> dataList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(dataList);
    }

    @Override
    public Result listFieldQuery(Map<String, Object> params) {
        Long number = Long.valueOf(String.valueOf(params.getOrDefault("number", 1)));
        Long size = Long.valueOf(String.valueOf(params.getOrDefault("size", 10)));
        IPage<AppHotelreservation> dataList = doMapper.listFieldQuery(new Page(number, size), params);
        return new Result("200", dataList);
    }
}
