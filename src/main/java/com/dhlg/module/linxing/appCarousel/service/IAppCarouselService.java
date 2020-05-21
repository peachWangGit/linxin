package com.dhlg.module.linxing.appCarousel.service;

import com.dhlg.module.linxing.appCarousel.entity.AppCarousel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since 2020-04-30
 */
public interface IAppCarouselService extends IService<AppCarousel> {

    Result customSaveOrUpdate(AppCarousel appCarousel);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);

}
