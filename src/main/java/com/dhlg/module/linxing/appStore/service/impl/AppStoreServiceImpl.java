package com.dhlg.module.linxing.appStore.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.linxing.appStore.entity.AppStore;
import com.dhlg.module.linxing.appStore.dao.AppStoreMapper;
import com.dhlg.module.linxing.appStore.service.IAppStoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.common.Dictionaries;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@Service
public class AppStoreServiceImpl extends ServiceImpl<AppStoreMapper, AppStore> implements IAppStoreService {

    @Autowired
    AppStoreMapper doMapper;

    @Override
    public Result customSaveOrUpdate(AppStore appStore) {
        //判断新增还是修改
        if (!StringUtils.isBlank(appStore.getId())) {
            //新增
            if(!updateById(appStore)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }
            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }

        if (!save(appStore)){
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
        IPage<AppStore> buttonConfigList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(buttonConfigList);
    }
}
