package com.dhlg.module.system.sysRole.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.sysMenu.entity.SysMenu;
import com.dhlg.module.system.sysRole.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.utils.common.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author xu
 * @since 2020-02-04
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getRoleList();

    IPage<SysRole> queryByCondition(Page page, @Param("parameter") Parameter parameter);

    List<SysMenu> getButMenu();

    Set<String> findRoles(@Param("roleIds") String[] roleList);
}
