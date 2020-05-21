package com.dhlg.module.linxing.appCarousel.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.linxing.appCarousel.entity.AppCarousel;
import com.dhlg.module.linxing.appCarousel.dao.AppCarouselMapper;
import com.dhlg.module.linxing.appCarousel.service.IAppCarouselService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.module.system.sysButton.entity.SysButtonConfig;
import com.dhlg.utils.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-04-30
 */
@Service
public class AppCarouselServiceImpl extends ServiceImpl<AppCarouselMapper, AppCarousel> implements IAppCarouselService {

    @Autowired
    AppCarouselMapper doMapper;

    @Override
    public Result customSaveOrUpdate(AppCarousel appCarousel) {
        //判断新增还是修改
        if (!StringUtils.isBlank(appCarousel.getId())) {
            //新增
            if(!updateById(appCarousel)){
                return new Result("500","",Dictionaries.UPDATE_FAILED);
            }
            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }

        if (!save(appCarousel)){
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
        IPage<AppCarousel> buttonConfigList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(buttonConfigList);
    }

}
