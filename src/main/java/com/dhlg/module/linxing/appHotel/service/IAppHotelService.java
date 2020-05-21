package com.dhlg.module.linxing.appHotel.service;

import com.dhlg.module.linxing.appHotel.entity.AppHotel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
public interface IAppHotelService extends IService<AppHotel> {

    Result customSaveOrUpdate(AppHotel appHotel);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);
}
