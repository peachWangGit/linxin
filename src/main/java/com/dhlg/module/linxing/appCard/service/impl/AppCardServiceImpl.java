package com.dhlg.module.linxing.appCard.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.linxing.appArticle.entity.AppArticle;
import com.dhlg.module.linxing.appCard.entity.AppCard;
import com.dhlg.module.linxing.appCard.dao.AppCardMapper;
import com.dhlg.module.linxing.appCard.service.IAppCardService;
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
 * @since 2020-05-03
 */
@Service
public class AppCardServiceImpl extends ServiceImpl<AppCardMapper, AppCard> implements IAppCardService {

    @Autowired
    AppCardMapper doMapper;

    @Override
    public Result customSaveOrUpdate(AppCard appCard) {
        //判断新增还是修改

        if (!StringUtils.isBlank(appCard.getId())) {
            //新增
            if(!updateById(appCard)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }
            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }

        if (!save(appCard)){
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
    public Result queryByCondition(Parameter parameter) {
        parameter.setDefault();
        IPage<AppArticle> buttonConfigList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(buttonConfigList);
    }

    @Override
    public Result deleteBatch(List<String> ids) {
        if (!removeByIds(ids)){
            return new Result("500","", Dictionaries.DELETE_FAILED);
        }
        return new Result("200","",Dictionaries.DELETE_SUCCESS);
    }
}
