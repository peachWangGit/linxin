package com.dhlg.module.system.sysButton.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.system.sysButton.entity.SysButtonConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since 2020-02-07
 */
public interface ISysButtonConfigService extends IService<SysButtonConfig> {

    boolean delete(String id);

    boolean deleteBatch(Collection<String> ids);

    IPage<SysButtonConfig> pageButtonConfig(Parameter parameter);

    Result customSaveOrUpdate(SysButtonConfig sysButtonConfig);

    Result queryByCondition(Parameter parameter);
}
