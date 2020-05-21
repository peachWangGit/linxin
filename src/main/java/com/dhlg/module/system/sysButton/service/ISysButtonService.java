package com.dhlg.module.system.sysButton.service;

import com.dhlg.module.system.sysButton.entity.SysButton;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.common.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since 2020-02-01
 */
public interface ISysButtonService extends IService<SysButton> {

    boolean deleteById(String id) throws Exception;

    Result insert(SysButton sysButton);
}
