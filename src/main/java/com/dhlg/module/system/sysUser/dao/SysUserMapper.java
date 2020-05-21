package com.dhlg.module.system.sysUser.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.sysRole.entity.SysRole;
import com.dhlg.module.system.sysUser.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.utils.common.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xu
 * @since 2020-01-07
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> getUserByName(String username);

    IPage<SysUser> queryByCondition(Page page, @Param("parameter") Parameter parameter);
}
