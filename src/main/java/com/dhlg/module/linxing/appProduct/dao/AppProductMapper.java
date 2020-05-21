package com.dhlg.module.linxing.appProduct.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.linxing.appProduct.entity.AppProduct;
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
public interface AppProductMapper extends BaseMapper<AppProduct> {

    IPage<AppProduct> queryByCondition(Page page, @Param("parameter")Parameter parameter);


    IPage<AppProduct> listFieldQuery(Page page, @Param("parameter")Map<String, Object> params);

    AppProduct getSaveId(AppProduct appProduct);
}
        