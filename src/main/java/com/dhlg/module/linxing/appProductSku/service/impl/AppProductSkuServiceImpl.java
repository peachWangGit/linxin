package com.dhlg.module.linxing.appProductSku.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.linxing.appProductSku.entity.AppProductSku;
import com.dhlg.module.linxing.appProductSku.dao.AppProductSkuMapper;
import com.dhlg.module.linxing.appProductSku.service.IAppProductSkuService;
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
 * @since 2020-05-05
 */
@Service
public class AppProductSkuServiceImpl extends ServiceImpl<AppProductSkuMapper, AppProductSku> implements IAppProductSkuService {

    @Autowired
    AppProductSkuMapper doMapper;

    @Override
    public Result customSaveOrUpdate(AppProductSku appProductSku) {
        appProductSku.setCreatedAt(GetLoginUser.getCurrentUserId());
        //判断新增还是修改
        if (!StringUtils.isBlank(appProductSku.getId())) {
            //新增
            if(!updateById(appProductSku)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }
            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }

        if (!save(appProductSku)){
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
        IPage<AppProductSku> buttonConfigList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(buttonConfigList);
    }

    public void deleteByProductId(int productId) {
        doMapper.deleteByProductId(productId);
    }
}
