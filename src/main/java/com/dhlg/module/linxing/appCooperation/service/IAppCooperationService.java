package com.dhlg.module.linxing.appCooperation.service;

import com.dhlg.module.linxing.appCooperation.entity.AppCooperation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
public interface IAppCooperationService extends IService<AppCooperation> {

    Result customSaveOrUpdate(AppCooperation appCooperation);

    Result delete(String id);

    Result queryByCondition(Parameter parameter);

    Result deleteBatch(List<String> ids);
}
