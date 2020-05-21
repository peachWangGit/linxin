package com.dhlg.module.linxing.appCooperation.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.linxing.appCooperation.entity.AppCooperation;
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
public interface AppCooperationMapper extends BaseMapper<AppCooperation> {

    IPage<AppCooperation> queryByCondition(Page page,@Param("parameter") Parameter parameter);

    int getBadgeNum();
}
