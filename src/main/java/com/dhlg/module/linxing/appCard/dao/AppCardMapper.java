package com.dhlg.module.linxing.appCard.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.linxing.appArticle.entity.AppArticle;
import com.dhlg.module.linxing.appCard.entity.AppCard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.utils.common.Parameter;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xu
 * @since 2020-05-03
 */
public interface AppCardMapper extends BaseMapper<AppCard> {

    IPage<AppArticle> queryByCondition(Page page, @Param("parameter")Parameter parameter);
}
