package com.dhlg.module.system.sysdic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.system.sysdic.entity.Option;
import com.dhlg.module.system.sysdic.entity.SysDic;
import com.dhlg.module.system.sysdic.entity.SysDicType;
import com.dhlg.module.system.sysdic.dao.SysDicTypeMapper;
import com.dhlg.module.system.sysdic.service.ISysDicTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class SysDicTypeServiceImpl extends ServiceImpl<SysDicTypeMapper, SysDicType> implements ISysDicTypeService {

    @Autowired
    SysDicServiceImpl sysDicService;

    @Autowired(required = false)
    SysDicTypeMapper sysDicTypeMapper;

    @Override
    @Transactional
    public int saveOrUpdateM(SysDicType sysDicType) {
        String currentUserId = GetLoginUser.getCurrentUserId();
//        String currentUserId = "2";
        Date currentDate = DateUtils.getCurrentDate();
        if (StringUtils.isEmpty(sysDicType.getId())) {
            //判断是否存在
            List<SysDicType> list = list(new QueryWrapper<SysDicType>()
                    .eq(SysDicType.DIC_TYPE, sysDicType.getDicType())
            );
            if (list.size() > 0) {
                return -1;
            }
            sysDicType.setId(StringUtils.uuid());
            sysDicType.setCreateUser(currentUserId);
            sysDicType.setCreateTime(currentDate);
            super.save(sysDicType);
            return 0;
        } else {
            sysDicType.setUpdateUser(currentUserId);
            sysDicType.setUpdateTime(currentDate);
            super.updateById(sysDicType);
            return 1;
        }
    }

    @Override
    public Result pageDicType(Parameter parameter) {
        parameter.setDefault();
        IPage<SysDicType> dicTypeList = sysDicTypeMapper.pageDicType(parameter.getPage(), parameter);

        return new Result(dicTypeList);

    }

    @Override
    @Transactional
    public boolean delete(String id) {
        //删除子表信息
        sysDicService.remove(new QueryWrapper<SysDic>()
                .eq(SysDic.DIC_TYPE_ID, id)
        );
        //删除主表信息
        return super.removeById(id);
    }

    @Override
    @Transactional
    public boolean deleteBatch(Collection<String> ids) {
        ids.forEach(i -> {
            this.delete(i);
        });
        return true;
    }

    @Override
    public List<Option> getType(String type) {
        return sysDicTypeMapper.getType(type);

    }
}
