package com.dhlg.module.system.sysButton.service.impl;

import com.dhlg.module.system.sysButton.entity.SysButton;
import com.dhlg.module.system.sysButton.dao.SysButtonMapper;
import com.dhlg.module.system.sysButton.service.ISysButtonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-02-01
 */
@Service
public class SysButtonServiceImpl extends ServiceImpl<SysButtonMapper, SysButton> implements ISysButtonService {

    @Autowired(required = false)
    SysButtonMapper buttonMapper;

    @Override
    public boolean deleteById(String id) throws Exception{
        boolean delete = super.removeById(id);
        if (delete) {
            return true;
        } else {
                throw new Exception("SysButton删除失败");
        }
    }

    @Override
    public Result insert(SysButton sysButton) {
        Result result = new Result();
        if (StringUtils.isBlank(sysButton.getId())){
            result.setMessage(Dictionaries.SAVE_SUCCESS);
            sysButton.setId(StringUtils.uuid());
            sysButton.setCreateTime(DateUtils.getCurrentDate());
            sysButton.setCreateUser(GetLoginUser.getCurrentUserId());

            if (!super.save(sysButton)) {
                result.buildFailedResult(Dictionaries.SAVE_FAILED, Dictionaries.SAVE_FAILED, Dictionaries.SAVE_FAILED);
            }

        }else {
            result.setMessage(Dictionaries.UPDATE_SUCCESS);
            sysButton.setUpdateTime(DateUtils.getCurrentDate());
            sysButton.setUpdateUser(GetLoginUser.getCurrentUserId());
            if (!super.updateById(sysButton)) {
                result.buildFailedResult(Dictionaries.SAVE_FAILED, Dictionaries.SAVE_FAILED, Dictionaries.SAVE_FAILED);
            }
        }
        result.setBody(sysButton);
        return result;
    }

    public List<SysButton> findAllButtonUrl() {
        return super.list();
    }

    public List<SysButton> findButtonUrl(String roleIds) {
        if (StringUtils.isBlank(roleIds)){
            return null;
        }
        String[] roleIdList=StringUtils.stb(roleIds);
        return buttonMapper.findButtonUrl(roleIdList);
    }
}
