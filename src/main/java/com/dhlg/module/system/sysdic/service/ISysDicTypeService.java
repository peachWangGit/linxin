package com.dhlg.module.system.sysdic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.module.system.sysdic.entity.Option;
import com.dhlg.module.system.sysdic.entity.SysDicType;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;

import java.util.Collection;
import java.util.List;

public interface ISysDicTypeService extends IService<SysDicType> {
    int saveOrUpdateM(SysDicType sysDicType);

    Result pageDicType(Parameter parameter);

    boolean delete(String id);

    boolean deleteBatch(Collection<String> ids);

    List<Option>  getType(String type);
}
