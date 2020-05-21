package com.dhlg.module.system.sysRole.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.system.sysMenu.entity.SysMenu;
import com.dhlg.module.system.sysRole.entity.SysRole;
import com.dhlg.module.system.sysRole.dao.SysRoleMapper;
import com.dhlg.module.system.sysRole.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.common.*;
import com.dhlg.utils.common.exception.UncheckedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-02-04
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    VerificationProperty verificationProperty;

    @Override
    public List<SysRole> getRoleList() {

        return sysRoleMapper.getRoleList();
    }

    @Override
    public Result saveorupdate(SysRole sysRole) {

        Result result = new Result();
        //判断更新还是新增
        if (StringUtils.isBlank(sysRole.getId())){
            sysRole.setId(StringUtils.uuid());
            //新增,1.判断是否重名
            boolean isRepeat=verificationProperty.lockAndVerify(sysRole,SysRole.NAME,sysRole.getId());
            if (isRepeat){
                throw new UncheckedException(Dictionaries.NAME_REPETITION,Dictionaries.NAME_REPETITION,Dictionaries.NAME_REPETITION);
            }
            sysRole.setCreateTime(DateUtils.getCurrentDate());
            sysRole.setCreateUser("admin");
            result.setBody(Dictionaries.SAVE_SUCCESS);
            //2.新增操作
            if (!save(sysRole)){
                throw new UncheckedException(Dictionaries.SAVE_FAILED,Dictionaries.SAVE_FAILED,Dictionaries.SAVE_FAILED);
            }
        }else {
            //更新,1.判断是否重名
            boolean isRepeat=verificationProperty.lockAndVerify(sysRole,SysRole.NAME,sysRole.getId());
            if (isRepeat){
                throw new UncheckedException(Dictionaries.NAME_REPETITION,Dictionaries.NAME_REPETITION,Dictionaries.NAME_REPETITION);
            }
            sysRole.setUpdateTime(DateUtils.getCurrentDate());
            sysRole.setUpdateUser("admin");
            result.setBody(Dictionaries.UPDATE_SUCCESS);
            //2.更新操作
            if (!updateById(sysRole)){
                throw new UncheckedException(Dictionaries.UPDATE_FAILED,Dictionaries.UPDATE_FAILED,Dictionaries.UPDATE_FAILED);
            }

        }
        return result;
    }

    @Override
    public Result deleteById(String id) {
        Result result = new Result();
        result.setBody(Dictionaries.DELETE_SUCCESS);
        if (!removeById(id)){
            result.setBody(Dictionaries.DELETE_FAILED);
        }
        return result;
    }

    @Override
    public Result queryByCondition(Parameter parameter) {
        //如果前端传递的分页数据为空，则设置为默认
        parameter.setDefault();
        IPage<SysRole> projectTeamIPage = sysRoleMapper.queryByCondition(parameter.getPage(), parameter);

        return new Result(projectTeamIPage);
    }

    @Override
    public Result batchDelete(Set<String> ids) {
        if (super.removeByIds(ids)){
            return new Result("200",Dictionaries.STATUS_UPDATE_SUCCESS,Dictionaries.STATUS_UPDATE_SUCCESS);
        }
        return new Result("400",Dictionaries.STATUS_UPDATE_FAILED,Dictionaries.STATUS_UPDATE_FAILED);
    }

    @Override
    public Result batchUpdate(List<SysRole> sysRoleList) {

        if (super.updateBatchById(sysRoleList, sysRoleList.size())){
            return new Result("更新用户状态成功！");
        }
        return new Result("400", Dictionaries.UPDATE_FAILED, Dictionaries.UPDATE_FAILED);
    }

    @Override
    public List<SysMenu> getButMenu() {
        List<SysMenu> sysMenus = sysRoleMapper.getButMenu();
        return InitTree.getRootNodes(sysMenus);
    }

    public Set<String> findRoles(String roleIds) {
        if (!StringUtils.isBlank(roleIds)){
            String[] roleList=StringUtils.stb(roleIds);
            return sysRoleMapper.findRoles(roleList);
        }else {
            return null;
        }

    }
}
