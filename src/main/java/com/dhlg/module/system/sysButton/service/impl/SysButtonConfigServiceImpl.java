package com.dhlg.module.system.sysButton.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.sysButton.entity.SysButtonConfig;
import com.dhlg.module.system.sysButton.dao.SysButtonConfigMapper;
import com.dhlg.module.system.sysButton.service.ISysButtonConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-02-07
 */
@Service
public class SysButtonConfigServiceImpl extends ServiceImpl<SysButtonConfigMapper, SysButtonConfig> implements ISysButtonConfigService {


    @Autowired(required = false)
    SysButtonConfigMapper mapper;

    @Autowired
    VerificationProperty verificationProperty;

    final static String TABLE_NAME = "sys_button_config";

    @Override
    public boolean delete(String id) {
        return super.removeById(id);
    }

    @Override
    public boolean deleteBatch(Collection<String> ids) {
        return super.removeByIds(ids);
    }

    @Override
    public IPage<SysButtonConfig> pageButtonConfig(Parameter parameter) {
        Page<SysButtonConfig> sysButtonConfigPage = new Page<>(parameter.getNumber(), parameter.getSize());
        if (parameter.getCondition().size() == 0) {
            return super.page(sysButtonConfigPage);
        } else {
            Map<String, String> condition = parameter.getCondition();
            return super.page(sysButtonConfigPage, new QueryWrapper<SysButtonConfig>()
                    .like(SysButtonConfig.BUTTON_CODE, condition.get("buttonCode"))
                    .or()
                    .like(SysButtonConfig.BUTTON_NAME, condition.get("buttonName"))
            );
        }

    }

    @Override
    public Result customSaveOrUpdate(SysButtonConfig sysButtonConfig) {
        Result result = new Result();
        String currentUserId = GetLoginUser.getCurrentUserId();
        Date currentDate = DateUtils.getCurrentDate();
        String requestId = StringUtils.uuid();
        if (StringUtils.isBlank(sysButtonConfig.getId())) {
            //新增
            result.setBody(Dictionaries.SAVE_SUCCESS);
            //校验编码是否重复
            if (propertyValueIsExist(sysButtonConfig, requestId)) {
                result.buildFailedResult(Dictionaries.CODE_REPETITION, Dictionaries.CODE_REPETITION,Dictionaries.CODE_REPETITION);
                return result;
            }

            sysButtonConfig.setId(StringUtils.uuid());
            sysButtonConfig.setCreateUser(currentUserId);
            sysButtonConfig.setCreateTime(currentDate);
            if (!super.save(sysButtonConfig)) result.setBody(Dictionaries.SAVE_FAILED);
        } else {
            //更新
            SysButtonConfig oldButton = getById(sysButtonConfig.getId());
            String oldCode = oldButton.getButtonCode();
            String newCode = sysButtonConfig.getButtonCode();
            if (!oldCode.equals(newCode)) {
                //校验编码是否重复
                if (propertyValueIsExist(sysButtonConfig, requestId)) {
                    result.buildFailedResult(Dictionaries.CODE_REPETITION, Dictionaries.CODE_REPETITION, Dictionaries.CODE_REPETITION);
                    return result;
                }
            }

            result.setBody(Dictionaries.UPDATE_SUCCESS);
            sysButtonConfig.setUpdateUser(currentUserId);
            sysButtonConfig.setUpdateTime(currentDate);
            if (!super.updateById(sysButtonConfig)) result.setBody(Dictionaries.UPDATE_FAILED);
        }

        verificationProperty.lockAndVerify(sysButtonConfig, SysButtonConfig.BUTTON_CODE, requestId);
        return result;
    }

    @Override
    public Result queryByCondition(Parameter parameter) {
        parameter.setDefault();
        IPage<SysButtonConfig> buttonConfigList = mapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(buttonConfigList);
    }

    //校验编码是否重复
    private boolean propertyValueIsExist(SysButtonConfig buttonConfig, String requestId) {
        return verificationProperty.lockAndVerify(buttonConfig,
                SysButtonConfig.BUTTON_CODE,
                requestId);
    }
}
