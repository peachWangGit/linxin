package com.dhlg.utils.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 描述:上传文件
 * User:whfch
 * Date:2020/4/16
 * Time:19:37
 */
public class uploadFileUtils {


    public static String uploadImg(MultipartFile file) {
        //获取上传文件名,包含后缀
        String originalFilename = file.getOriginalFilename();
        //获取后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存的文件名
        String dFileName = UUID.randomUUID()+substring;
        //保存路径
        //springboot 默认情况下只能加载 resource文件夹下静态资源文件

        String path = Dictionaries.LOCALIMGSRC;

        //生成保存文件
        File uploadFile = new File(path+dFileName);
        //将上传文件保存到路径
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Dictionaries.IMGSRC+dFileName;
    }
    public static boolean delete(String url) {
        if (!StringUtils.isBlank(url)){
            String trueLocalImgSrc=url.replace(Dictionaries.IMGSRC,Dictionaries.LOCALIMGSRC);
            File file = new File(trueLocalImgSrc);
            try {
                file.delete();
            }catch (Exception e){
                return false;
            }
            return true;
        }
        return false;
    }

}
