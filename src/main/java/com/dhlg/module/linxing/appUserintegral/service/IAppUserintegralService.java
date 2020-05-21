package com.dhlg.module.linxing.appUserintegral.service;

import com.dhlg.module.linxing.appUserintegral.entity.AppUserintegral;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since
 */
public interface IAppUserintegralService extends IService<AppUserintegral> {

    Result customSaveOrUpdate(AppUserintegral appUserintegral);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);

    Result listFieldQuery(Map<String, Object> params);
}
