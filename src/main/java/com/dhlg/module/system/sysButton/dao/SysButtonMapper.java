package com.dhlg.module.system.sysButton.dao;

import com.dhlg.module.system.sysButton.entity.SysButton;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xu
 * @since 2020-02-01
 */
public interface SysButtonMapper extends BaseMapper<SysButton> {

    List<SysButton> findButtonUrl(@Param("roleIds") String[] roleIdList);
}
