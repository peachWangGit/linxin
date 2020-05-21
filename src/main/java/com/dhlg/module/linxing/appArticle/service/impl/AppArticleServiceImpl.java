package com.dhlg.module.linxing.appArticle.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.linxing.appArticle.entity.AppArticle;
import com.dhlg.module.linxing.appArticle.dao.AppArticleMapper;
import com.dhlg.module.linxing.appArticle.service.IAppArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-02
 */
@Service
public class AppArticleServiceImpl extends ServiceImpl<AppArticleMapper, AppArticle> implements IAppArticleService {

    @Autowired
    AppArticleMapper doMapper;

    @Override
    public Result customSaveOrUpdate(AppArticle appArticle) {
        //判断新增还是修改
        appArticle.setCreatedDate(DateUtils.getCurrentDate());
        if (!StringUtils.isBlank(appArticle.getId())) {
            //新增
            appArticle.setReading(0);
            if(!updateById(appArticle)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }
            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }

        if (!save(appArticle)){
            return new Result("500","", Dictionaries.SAVE_FAILED);
        }
        return new Result("200","",Dictionaries.SAVE_SUCCESS);
    }

    @Override
    public Result delete(String id) {
        if (!removeById(id)){
            return new Result("500","", Dictionaries.DELETE_FAILED);
        }
        return new Result("200","",Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result deleteBatch(List<String> ids) {
        if (!removeByIds(ids)){
            return new Result("500","", Dictionaries.DELETE_FAILED);
        }
        return new Result("200","",Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result queryByCondition(Parameter parameter) {
        parameter.setDefault();
        IPage<AppArticle> buttonConfigList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(buttonConfigList);
    }
}
