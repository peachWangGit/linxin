package com.dhlg.module.linxing.appConfiguration.service;

import com.dhlg.module.linxing.appConfiguration.entity.AppConfiguration;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.common.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since 2020-05-06
 */
public interface IAppConfigurationService extends IService<AppConfiguration> {

    Result getAllData();

    Result changeData(List<AppConfiguration> lists);
}
