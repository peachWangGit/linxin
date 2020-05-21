package com.dhlg.module.system.sysFile.service;

import com.dhlg.module.system.sysFile.entity.SysFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since 2020-05-01
 */
public interface ISysFileService extends IService<SysFile> {

    Result uploadFile(MultipartFile file);

    Result deleteFile(String url);

    Result deleteBatch(List<String> urls);

    Result queryByCondition(Parameter parameter);
}
