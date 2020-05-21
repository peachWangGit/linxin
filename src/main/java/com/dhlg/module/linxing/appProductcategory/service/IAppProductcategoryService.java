package com.dhlg.module.linxing.appProductcategory.service;

import com.dhlg.module.linxing.appProductcategory.entity.AppProductcategory;
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
public interface IAppProductcategoryService extends IService<AppProductcategory> {

    Result customSaveOrUpdate(AppProductcategory appProductcategory);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);
}
