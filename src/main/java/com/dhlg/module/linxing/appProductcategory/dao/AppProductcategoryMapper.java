package com.dhlg.module.linxing.appProductcategory.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.linxing.appProductcategory.entity.AppProductcategory;
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
public interface AppProductcategoryMapper extends BaseMapper<AppProductcategory> {

    IPage<AppProductcategory> queryByCondition(Page page, @Param("parameter")Parameter parameter);
}
        