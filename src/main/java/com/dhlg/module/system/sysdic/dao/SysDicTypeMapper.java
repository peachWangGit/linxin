package com.dhlg.module.system.sysdic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.sysdic.entity.Option;
import com.dhlg.module.system.sysdic.entity.SysDicType;
import com.dhlg.utils.common.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDicTypeMapper extends BaseMapper<SysDicType> {
    List<Option> getType(@Param("type") String type);

    IPage<SysDicType> pageDicType(Page page, @Param("parameter") Parameter parameter);

}
