package com.dhlg.module.linxing.appProduct.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.linxing.appProduct.entity.AppProduct;
import com.dhlg.module.linxing.appProduct.dao.AppProductMapper;
import com.dhlg.module.linxing.appProduct.service.IAppProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.module.linxing.appProductSku.entity.AppProductSku;
import com.dhlg.module.linxing.appProductSku.service.impl.AppProductSkuServiceImpl;
import com.dhlg.module.linxing.appProductSpecification.entity.AppProductSpecification;
import com.dhlg.module.linxing.appProductSpecification.service.impl.AppProductSpecificationServiceImpl;
import com.dhlg.module.system.sysUser.entity.SysUser;
import com.dhlg.utils.common.*;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@Service
public class AppProductServiceImpl extends ServiceImpl<AppProductMapper, AppProduct> implements IAppProductService {

    @Autowired
    AppProductMapper doMapper;

    @Autowired
    AppProductSkuServiceImpl skuService;

    @Autowired
    AppProductSpecificationServiceImpl specificationService;

    @Override
    @Transactional
    public Result customSaveOrUpdate(AppProduct appProduct) {
        appProduct.setCreatedAt(GetLoginUser.getCurrentUserId());
        SysUser user=GetLoginUser.getCurrentUser();
        if (StringUtils.isBlank(user)){
            return new Result("500","", "您还未的登录");
        }
        appProduct.setCreatedAt(user.getId());
        if (!user.getDepartmentId().equals("0")){
            appProduct.setStoreId(Integer.valueOf(user.getDepartmentId()));
        }

        //判断新增还是修改
        if (!StringUtils.isBlank(appProduct.getId())) {
            //更新
            if(!updateById(appProduct)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }

            for (AppProductSku productSku:appProduct.getAppProductSkuList()){
                productSku.setCreatedAt(user.getId());
                productSku.setProductId(appProduct.getId());

            }
            for (AppProductSpecification specification:appProduct.getProductSpecificationList()){
                specification.setProductId(appProduct.getId());
            }

            specificationService.deleteByProductId(appProduct.getId());
            specificationService.saveBatch(appProduct.getProductSpecificationList());

            skuService.deleteByProductId(appProduct.getId());
            skuService.saveBatch(appProduct.getAppProductSkuList());

            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }

        appProduct.setCreatedAt(user.getId());
        save(appProduct);
        int num=appProduct.getId();

        if (!StringUtils.isBlank(appProduct.getAppProductSkuList())){
            for (AppProductSku productSku:appProduct.getAppProductSkuList()){
                productSku.setCreatedAt(user.getId());
                productSku.setProductId(num);

            }
            for (AppProductSpecification specification:appProduct.getProductSpecificationList()){
                specification.setProductId(num);
            }
            specificationService.saveBatch(appProduct.getProductSpecificationList());
            skuService.saveBatch(appProduct.getAppProductSkuList());
        }

        return new Result("200","",Dictionaries.SAVE_SUCCESS);
    }

    @Override
    @Transactional
    public Result delete(String id) {
        specificationService.deleteByProductId(Integer.valueOf(id));
        skuService.deleteByProductId(Integer.valueOf(id));
        if (!removeById(id)){
            return new Result("500","", Dictionaries.DELETE_FAILED);
        }
        return new Result("200","",Dictionaries.DELETE_SUCCESS);
    }

    @Override
    @Transactional
    public Result deleteBatch(List<String> ids) {
        for(String id:ids){
            specificationService.deleteByProductId(Integer.valueOf(id));
            skuService.deleteByProductId(Integer.valueOf(id));
        }
        if (!removeByIds(ids)){
            return new Result("500","", Dictionaries.DELETE_FAILED);
        }
        return new Result("200","",Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result queryByCondition(Parameter parameter) {
        parameter.setDefault();
        String userId = GetLoginUser.getCurrentUserId();
        if (userId.equals("1")){
            parameter.getCondition().put("userId","");
        }else {
            parameter.getCondition().put("userId",userId);
        }

        IPage<AppProduct> buttonConfigList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(buttonConfigList);
    }

    @Override
    public Result listFieldQuery(Map<String, Object> params) {
        Long number = Long.valueOf(String.valueOf(params.getOrDefault("number", 1)));
        Long size = Long.valueOf(String.valueOf(params.getOrDefault("size", 10)));
        SysUser user = GetLoginUser.getCurrentUser();
        params.put("departmentId",Integer.parseInt(user.getDepartmentId()));

        IPage<AppProduct> appProductIPage = doMapper.listFieldQuery(new Page(number, size), params);
        return new Result("200", appProductIPage);
    }
}
