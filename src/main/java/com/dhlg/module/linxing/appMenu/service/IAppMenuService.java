package com.dhlg.module.linxing.appMenu.service;

import com.dhlg.module.linxing.appMenu.entity.AppMenu;
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
 * @since 2020-05-02
 */
public interface IAppMenuService extends IService<AppMenu> {

    Result customSaveOrUpdate(AppMenu appMenu);

    boolean delete(String id);

    boolean deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);
}
