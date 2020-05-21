package com.dhlg.module.linxing.appUser.service;

import com.dhlg.module.linxing.appUser.entity.AppUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since
 */
public interface IAppUserService extends IService<AppUser> {

    Result customSaveOrUpdate(AppUser appUser);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);

    Result changeJIfen(AppUser appUser);

    Result getCount();
}
