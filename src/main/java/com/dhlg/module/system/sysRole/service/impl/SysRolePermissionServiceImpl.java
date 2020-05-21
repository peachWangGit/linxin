package com.dhlg.module.system.sysRole.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhlg.module.system.sysRole.entity.SysRolePermission;
import com.dhlg.module.system.sysRole.dao.SysRolePermissionMapper;
import com.dhlg.module.system.sysRole.service.ISysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-02-06
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;
    public List<String> getButByRoleId(String id) {
        List<String> a = new ArrayList<>();
        //已勾选的按钮id
        List<SysRolePermission> permissionButtonList = super.list(new QueryWrapper<SysRolePermission>()
                .isNotNull(SysRolePermission.BUTTON_ID)
                .ne(SysRolePermission.BUTTON_ID, "")
                .eq(SysRolePermission.ROLE_ID, id)
        );
        List<String> permissionMenuList = sysRolePermissionMapper.getPermissionMenu(id);
        //已勾选的最下级不带按钮的菜单id

        permissionButtonList.forEach(i -> {
            a.add(i.getButtonId());
        });
        permissionMenuList.forEach(i -> {
            a.add(i);
        });
        return a;
    }

    @Override
    @Transactional
    public boolean batchSave(String roleId, List<SysRolePermission> sysRolePermissions) {
        super.remove(new QueryWrapper<SysRolePermission>().eq(
                SysRolePermission.ROLE_ID, roleId
        ));
        //不为空
        if (sysRolePermissions.size() > 0) {
            sysRolePermissions.forEach(i -> i.setId(StringUtils.uuid()));
            return super.saveBatch(sysRolePermissions);
        }
        return true;
    }
}
