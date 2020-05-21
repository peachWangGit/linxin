package com.dhlg.module.system.sysMenu.service;

import com.dhlg.module.system.sysMenu.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.common.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since 2020-01-07
 */
public interface ISysMenuService extends IService<SysMenu> {


    List<SysMenu> getNodeList(Map<String, Object> params);

    Result saveNode(SysMenu sysMenu);

    boolean deleteNode(String id);
}
