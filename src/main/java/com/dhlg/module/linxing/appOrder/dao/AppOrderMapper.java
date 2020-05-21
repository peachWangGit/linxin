package com.dhlg.module.linxing.appOrder.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.linxing.appOrder.entity.AppOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.utils.common.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* <p>
*  Mapper 接口
* </p>
*
* @author xu
* @since
*/
public interface AppOrderMapper extends BaseMapper<AppOrder> {

    IPage<AppOrder> queryByCondition(Page page, @Param("parameter")Parameter parameter);

    IPage<AppOrder> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        