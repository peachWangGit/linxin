package com.dhlg.module.linxing.appHotel.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.linxing.appHotel.entity.AppHotel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.utils.common.Parameter;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
public interface AppHotelMapper extends BaseMapper<AppHotel> {

    IPage<AppHotel> queryByCondition(Page page, @Param("parameter")Parameter parameter);
}
