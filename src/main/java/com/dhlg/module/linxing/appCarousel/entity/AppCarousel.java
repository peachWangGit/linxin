package com.dhlg.module.linxing.appCarousel.entity;

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
 * @since 2020-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AppCarousel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
     * 跳转的id
     */
    @TableField("jump_id")
    private String jumpId;

    /**
     * 此轮播所在页面
     */
    @TableField("current_page")
    private String currentPage;

    /**
     * 优先级
     */
    @TableField("priority")
    private Integer priority;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;


    public static final String ID = "id";

    public static final String IMG_URL = "img_url";

    public static final String JUMP_PAGE = "jump_page";

    public static final String JUMP_ID = "jump_id";

    public static final String CURRENT_PAGE = "current_page";

    public static final String PRIORITY = "priority";

}
