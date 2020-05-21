package com.dhlg.module.system.sysdic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.system.sysdic.entity.SysDic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.exception.OperatorException;

import java.io.IOException;
import java.util.Collection;


public interface ISysDicService extends IService<SysDic> {

    IPage<SysDic> pageDic(Parameter parameter);

    Result listDic(String dicTypeId) throws IOException, OperatorException;

    boolean delete(String id);

    boolean deleteBatch(Collection<String> ids);

    int saveOrUpdateM(SysDic sysDic);

    Result queryAllDic();

}
