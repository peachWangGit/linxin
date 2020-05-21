package com.dhlg.module.linxing.appArticle.service;

import com.dhlg.module.linxing.appArticle.entity.AppArticle;
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
 * @since 2020-05-02
 */
public interface IAppArticleService extends IService<AppArticle> {

    Result customSaveOrUpdate(AppArticle appArticle);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);
}
