package com.dhlg.module.linxing.appProductSku.service;

import com.dhlg.module.linxing.appProductSku.entity.AppProductSku;
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
public interface IAppProductSkuService extends IService<AppProductSku> {

    Result customSaveOrUpdate(AppProductSku appProductSku);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);
}
