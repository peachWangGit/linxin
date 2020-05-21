package com.dhlg.module.linxing.appCard.service;

import com.dhlg.module.linxing.appCard.entity.AppCard;
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
 * @since 2020-05-03
 */
public interface IAppCardService extends IService<AppCard> {

    Result customSaveOrUpdate(AppCard appCard);

    Result delete(String id);

    Result queryByCondition(Parameter parameter);

    Result deleteBatch(List<String> ids);
}
