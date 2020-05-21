package com.dhlg.module.linxing.appOrderItem.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.linxing.appOrderItem.entity.AppOrderItem;
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
public interface AppOrderItemMapper extends BaseMapper<AppOrderItem> {

    IPage<AppOrderItem> queryByCondition(Page page, @Param("parameter")Parameter parameter);

    IPage<AppOrderItem> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        