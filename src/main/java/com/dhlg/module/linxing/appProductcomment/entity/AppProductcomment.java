package com.dhlg.module.linxing.appProductcomment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 *
 * </p>
 *
 * @author xu
 * @since 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("app_productcomment")
public class AppProductcomment implements Serializable {

    private static final long serialVersionUID = 1L;

     /**
     *id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 内容
     */
    @TableField("content")
    private String content;
    /**
     * 评论时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("create_time")
    private Date createTime;
    /**
     * 分数 1-5
     */
    @TableField("fraction")
    private Integer fraction;
    /**
     * 评论图片
     */
    @TableField("images")
    private String images;
    /**
     * 评论id
     */
    @TableField("parend_id")
    private Integer parendId;
    /**
     * 关联的用户
     */
    @TableField("product_id")
    private Integer productId;
    /**
     * 关联的用户
     */
    @TableField("user_id")
    private Integer userId;

}
