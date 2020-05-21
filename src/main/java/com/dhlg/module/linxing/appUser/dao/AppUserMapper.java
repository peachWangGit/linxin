package com.dhlg.module.linxing.appUser.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.linxing.appUser.entity.AppUser;
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
public interface AppUserMapper extends BaseMapper<AppUser> {

    IPage<AppUser> queryByCondition(Page page,@Param("parameter") Parameter parameter);

    int getProductCount(@Param("parameter")Map<String, Object> params);

    int getOrderCount(@Param("parameter")Map<String, Object> params);

    int getProductAlert(@Param("parameter")Map<String, Object> params);
}
        