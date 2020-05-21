package com.dhlg.module.linxing.appProductSpecification.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.linxing.appProductSpecification.entity.AppProductSpecification;
import com.dhlg.module.linxing.appProductSpecification.dao.AppProductSpecificationMapper;
import com.dhlg.module.linxing.appProductSpecification.service.IAppProductSpecificationService;
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
public class AppProductSpecificationServiceImpl extends ServiceImpl<AppProductSpecificationMapper, AppProductSpecification> implements IAppProductSpecificationService {

    @Autowired
    AppProductSpecificationMapper doMapper;

    @Override
    public Result customSaveOrUpdate(AppProductSpecification appProductSpecification) {
        //判断新增还是修改
        if (!StringUtils.isBlank(appProductSpecification.getId())) {
            //新增
            if(!updateById(appProductSpecification)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }
            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }

        if (!save(appProductSpecification)){
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
        IPage<AppProductSpecification> buttonConfigList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(buttonConfigList);
    }

    public void deleteByProductId(Integer productId) {
        doMapper.deleteByProductId(productId);
    }
}
