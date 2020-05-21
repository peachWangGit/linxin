package com.dhlg.module.system.sysLoginLog.service;

import com.dhlg.module.system.sysLoginLog.entity.SysLoginLog;
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
 * @since 2020-02-22
 */
public interface ISysLoginLogService extends IService<SysLoginLog> {

    Result queryByCondition(Parameter parameter);

    Result batchDeleteByIds(List<String> ids);
}
