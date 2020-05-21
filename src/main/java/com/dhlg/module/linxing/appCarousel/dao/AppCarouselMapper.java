package com.dhlg.module.linxing.appCarousel.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.linxing.appCarousel.entity.AppCarousel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.module.system.sysButton.entity.SysButtonConfig;
import com.dhlg.utils.common.Parameter;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xu
 * @since 2020-04-30
 */
public interface AppCarouselMapper extends BaseMapper<AppCarousel> {

    IPage<AppCarousel> queryByCondition(Page page, @Param("parameter")Parameter parameter);
}
