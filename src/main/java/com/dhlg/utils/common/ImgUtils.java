package com.dhlg.utils.common;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

/**
 * 描述
 * User:whfch
 * Date:2020/2/14
 * Time:1:34
 */
public class ImgUtils {

    /**
     * 功能：对图片进行转换流，并进行加密
     * @param src 图片的src
     * @return
     */
    public static byte[] dealImgOfByte(String src){
        String str = "data:image/png;base64,";
        String replaceStr = src.toString().replace(str, "");
        //将字符串转换为图片流
        byte[] bytes = Base64.decodeBase64(replaceStr);
        //将图片流Base64加密
        String img = new BASE64Encoder().encode(bytes);
        return img.getBytes();
    }

}
