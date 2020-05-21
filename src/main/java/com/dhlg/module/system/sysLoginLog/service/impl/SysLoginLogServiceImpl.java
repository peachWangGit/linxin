package com.dhlg.module.system.sysLoginLog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.system.sysLoginLog.entity.SysLoginLog;
import com.dhlg.module.system.sysLoginLog.dao.SysLoginLogMapper;
import com.dhlg.module.system.sysLoginLog.service.ISysLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.common.DateUtils;
import com.dhlg.utils.common.Dictionaries;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-02-22
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements ISysLoginLogService {

    @Autowired(required = false)
    SysLoginLogMapper loginLogMapper;

    @Override
    public Result queryByCondition(Parameter parameter) {
        parameter.setDefault();

        //把前端传的时间戳转成日期字符串
        Map<String, String> condition = parameter.getCondition();
        if (null != condition && null != condition.get("startTime")&& !condition.get("startTime").equals("")) {
            condition.put("startTime", DateUtils.convertTimestampToString(Long.valueOf(condition.get("startTime"))));
        }
        if (null != condition && null != condition.get("endTime")&& !condition.get("endTime").equals("")) {
            condition.put("endTime", DateUtils.convertTimestampToString(Long.valueOf(condition.get("endTime"))));
        }

        IPage<SysLoginLog> loginLogList = loginLogMapper.queryByCondition(parameter.getPage(), parameter);

        return new Result(loginLogList);
    }

    @Override
    public Result batchDeleteByIds(List<String> ids) {
        QueryWrapper<SysLoginLog> wrapper = new QueryWrapper<>();
        ids.forEach(id -> {
            wrapper.eq("id", id);
        });

        if(super.removeByIds(ids)){
            return new Result("200", Dictionaries.DELETE_SUCCESS,Dictionaries.DELETE_SUCCESS);
        }
        return  new Result("400",Dictionaries.DELETE_FAILED,Dictionaries.DELETE_FAILED);
    }
}
