package com.dhlg.module.system.sysFile.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.sysFile.entity.SysFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.utils.common.Parameter;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xu
 * @since 2020-05-01
 */
public interface SysFileMapper extends BaseMapper<SysFile> {

    IPage<SysFile> queryByCondition(Page page, Parameter parameter);
}
