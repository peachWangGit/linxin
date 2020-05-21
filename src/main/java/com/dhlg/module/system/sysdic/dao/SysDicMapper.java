package com.dhlg.module.system.sysdic.dao;

import com.dhlg.module.system.sysdic.entity.Option;
import com.dhlg.module.system.sysdic.entity.SysDic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDicMapper extends BaseMapper<SysDic> {

    List<SysDic> queryByTypeId(@Param("dicTypeId") String dicTypeId);

    List<Option> queryAllDic();

}
