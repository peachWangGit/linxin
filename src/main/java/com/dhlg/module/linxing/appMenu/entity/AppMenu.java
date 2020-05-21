package com.dhlg.module.linxing.appMenu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xu
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AppMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单文本
     */
    @TableField("text")
    private String text;

    /**
     * 图片地址
     */
    @TableField("img_url")
    private String imgUrl;

    /**
     * 跳转页面名
     */
    @TableField("jump_page")
    private String jumpPage;

    /**
     * 此菜单所在页面
     */
    @TableField("current_page")
    private String currentPage;


    public static final String ID = "id";

    public static final String TEXT = "text";

    public static final String IMG_URL = "img_url";

    public static final String JUMP_PAGE = "jump_page";

    public static final String CURRENT_PAGE = "current_page";

}
