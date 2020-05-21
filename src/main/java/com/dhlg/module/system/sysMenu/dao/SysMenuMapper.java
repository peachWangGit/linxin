package com.dhlg.module.system.sysMenu.dao;

import com.dhlg.module.system.sysMenu.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xu
 * @since 2020-01-07
 */
@Component
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> findMenu();

    List<SysMenu> findMenuByUserName(@Param("userName") String userName);

    List<SysMenu> queryAll();

    List<SysMenu> findMenuByroleIds(@Param("roleIds") String[] roleIds);





}
