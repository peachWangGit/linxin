package com.dhlg.module.linxing.appProductSku.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.linxing.appProductSku.entity.AppProductSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.utils.common.Parameter;
import org.apache.ibatis.annotations.Param;

/**
* <p>
*  Mapper 接口
* </p>
*
* @author xu
* @since
*/
public interface AppProductSkuMapper extends BaseMapper<AppProductSku> {

    IPage<AppProductSku> queryByCondition(Page page, @Param("parameter")Parameter parameter);

    void deleteByProductId(int productId);
}
        