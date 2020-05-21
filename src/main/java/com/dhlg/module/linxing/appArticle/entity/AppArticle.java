package com.dhlg.module.linxing.appArticle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class AppArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 封面
     */
    @TableField("cover_image")
    private String coverImage;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 图片
     */
    @TableField("images")
    private String images;

    /**
     * 创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("created_date")
    private Date createdDate;

    /**
     * 阅读量
     */
    @TableField("reading")
    private Integer reading;

    /**
     * 是否置顶
     */
    @TableField("top")
    private Integer top;


    public static final String ID = "id";

    public static final String COVER_IMAGE = "cover_image";

    public static final String TITLE = "title";

    public static final String CONTENT = "content";

    public static final String IMAGES = "images";

    public static final String CREATED_DATE = "created_date";

    public static final String READING = "reading";

    public static final String TOP = "top";

}
