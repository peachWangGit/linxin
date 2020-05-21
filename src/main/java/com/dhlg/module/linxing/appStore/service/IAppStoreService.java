package com.dhlg.module.linxing.appStore.service;

import com.dhlg.module.linxing.appStore.entity.AppStore;
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
public interface IAppStoreService extends IService<AppStore> {

    Result customSaveOrUpdate(AppStore appStore);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);
}
