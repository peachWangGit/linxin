package com.dhlg.module.system.sysButton.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.sysButton.entity.SysButtonConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.utils.common.Parameter;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xu
 * @since 2020-02-07
 */
public interface SysButtonConfigMapper extends BaseMapper<SysButtonConfig> {

    IPage<SysButtonConfig> queryByCondition(Page page, Parameter parameter);
}
