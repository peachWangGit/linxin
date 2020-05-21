package com.dhlg.module.system.sysdic.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.module.system.sysdic.dao.SysDicMapper;
import com.dhlg.module.system.sysdic.entity.Option;
import com.dhlg.module.system.sysdic.entity.SysDic;
import com.dhlg.module.system.sysdic.service.ISysDicService;
import com.dhlg.utils.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysDicServiceImpl extends ServiceImpl<SysDicMapper, SysDic> implements ISysDicService {

    @Autowired(required = false)
    SysDicMapper sysDicMapper;

    @Autowired
    VerificationProperty verificationProperty;


    @Override
    public IPage<SysDic> pageDic(Parameter parameter) {
        return null;
    }

    @Override
    public Result listDic(String dicTypeId){
        List<SysDic> sysDicList = sysDicMapper.queryByTypeId(dicTypeId);
        return new Result(sysDicList);
    }

    @Override
    public boolean delete(String id) {
        return super.removeById(id);
    }

    @Override
    public boolean deleteBatch(Collection<String> ids) {
        return super.removeByIds(ids);
    }

    @Override
    public int saveOrUpdateM(SysDic sysDic) {
        String currentUserId = GetLoginUser.getCurrentUserId();
        Date currentDate = DateUtils.getCurrentDate();
        String requestId = StringUtils.uuid();
        if (StringUtils.isEmpty(sysDic.getId())) {
            //校验编码是否重复
            sysDic.setId(StringUtils.uuid());
            sysDic.setCreateUser(currentUserId);
            sysDic.setCreateTime(currentDate);
            super.save(sysDic);
            verificationProperty.lockAndVerify(sysDic, SysDic.DIC_VALUE, requestId);
            return 0;
        } else {
            SysDic oldDic = getById(sysDic.getId());
            sysDic.setUpdateUser(currentUserId);
            sysDic.setUpdateTime(currentDate);
            super.updateById(sysDic);
            verificationProperty.lockAndVerify(sysDic, SysDic.DIC_VALUE, requestId);
            return 1;
        }
    }

    @Override
    public Result queryAllDic() {
        List<Option> list = sysDicMapper.queryAllDic();
        Map<String,List<Option>> map = list.stream().collect(Collectors.groupingBy(Option::getType));
        return new Result(map);
    }


}
