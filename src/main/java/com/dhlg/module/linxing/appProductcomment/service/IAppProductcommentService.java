package com.dhlg.module.linxing.appProductcomment.service;

import com.dhlg.module.linxing.appProductcomment.entity.AppProductcomment;
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
public interface IAppProductcommentService extends IService<AppProductcomment> {

    Result customSaveOrUpdate(AppProductcomment appProductcomment);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);

    Result listFieldQuery(Map<String, Object> params);
}
