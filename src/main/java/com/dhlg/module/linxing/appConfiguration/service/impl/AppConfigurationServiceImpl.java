package com.dhlg.module.linxing.appConfiguration.service.impl;

import com.dhlg.module.linxing.appConfiguration.entity.AppConfiguration;
import com.dhlg.module.linxing.appConfiguration.dao.AppConfigurationMapper;
import com.dhlg.module.linxing.appConfiguration.service.IAppConfigurationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.common.Dictionaries;
import com.dhlg.utils.common.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-06
 */
@Service
public class AppConfigurationServiceImpl extends ServiceImpl<AppConfigurationMapper, AppConfiguration> implements IAppConfigurationService {

    @Override
    public Result getAllData() {
        List<AppConfiguration> lists = list();
        AppConfiguration appConfiguration = lists.get(4);
        lists.remove(4);
        lists.add(1,appConfiguration);

        return new Result("200",lists,"");
    }

    @Override
    public Result changeData(List<AppConfiguration> lists) {
        if (!updateBatchById(lists)){
            return new Result("500","", Dictionaries.UPDATE_FAILED);
        }

        return new Result("200","", Dictionaries.UPDATE_SUCCESS);
    }
}
