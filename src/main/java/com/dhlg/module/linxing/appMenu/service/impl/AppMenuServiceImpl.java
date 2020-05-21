package com.dhlg.module.linxing.appMenu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.linxing.appMenu.entity.AppMenu;
import com.dhlg.module.linxing.appMenu.dao.AppMenuMapper;
import com.dhlg.module.linxing.appMenu.service.IAppMenuService;
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
 * @since 2020-05-02
 */
@Service
public class AppMenuServiceImpl extends ServiceImpl<AppMenuMapper, AppMenu> implements IAppMenuService {

    @Autowired
    AppMenuMapper doMapper;

    @Override
    public Result customSaveOrUpdate(AppMenu appMenu) {
        //判断新增还是修改
        if (!StringUtils.isBlank(appMenu.getId())) {
            //新增
            if(!updateById(appMenu)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }
            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }

        if (!save(appMenu)){
            return new Result("500","", Dictionaries.SAVE_FAILED);
        }
        return new Result("200","",Dictionaries.SAVE_SUCCESS);
    }

    @Override
    public boolean delete(String id) {
        return super.removeById(id);
    }

    @Override
    public boolean deleteBatch(List<String> ids) {
        return super.removeByIds(ids);
    }

    @Override
    public Result queryByCondition(Parameter parameter) {
        parameter.setDefault();
        IPage<AppMenu> buttonConfigList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(buttonConfigList);
    }
}
