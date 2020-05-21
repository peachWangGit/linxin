package com.dhlg.module.system.sysFile.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectById;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.system.sysFile.entity.SysFile;
import com.dhlg.module.system.sysFile.dao.SysFileMapper;
import com.dhlg.module.system.sysFile.service.ISysFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-01
 */
@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements ISysFileService {

    @Autowired
    SysFileMapper doMapper;

    @Override
    public Result uploadFile(MultipartFile file) {
        //获取上传图片的路径
        String name = uploadFileUtils.uploadImg(file);
        SysFile sysFile = new SysFile();
        sysFile.setId(StringUtils.uuid());
        sysFile.setCreateTime(DateUtils.getCurrentDate());
        sysFile.setCreateUser(GetLoginUser.getCurrentUserId());
        sysFile.setUrl(name);

        if (!save(sysFile)){
            return new Result("400","",Dictionaries.UPLOAD_ERROR);
        }
        return new Result("200",sysFile,Dictionaries.UPLOAD_SUCCESS);
    }

    @Override
    public Result deleteFile(String id) {
        if (!deleteFile2(id)){
            return new Result("500","","删除数据图片失败");
        }

        return new Result("200","","删除图片成功");
    }

    @Override
    public Result deleteBatch(List<String> ids) {

        for (String url: ids) {
            if (!deleteFile2(url)){
                return new Result("500","","删除失败");
            }
        }

        return new Result("200","","删除成功");
    }

    private boolean deleteFile2(String id) {
        SysFile file = getById(id);
        if (!uploadFileUtils.delete(file.getUrl())){
            return false;
        }

        if (!removeById(id)){
            return false;
        }
        return true;
    }

    @Override
    public Result queryByCondition(Parameter parameter) {
        parameter.setDefault();
        IPage<SysFile> buttonConfigList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(buttonConfigList);
    }

}
