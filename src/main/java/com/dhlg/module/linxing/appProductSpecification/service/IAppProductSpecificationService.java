package com.dhlg.module.linxing.appProductSpecification.service;

import com.dhlg.module.linxing.appProductSpecification.entity.AppProductSpecification;
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
 * @since
 */
public interface IAppProductSpecificationService extends IService<AppProductSpecification> {

    Result customSaveOrUpdate(AppProductSpecification appProductSpecification);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);
}
