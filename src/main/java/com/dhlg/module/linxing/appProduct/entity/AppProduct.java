package com.dhlg.module.linxing.appProduct.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.dhlg.module.linxing.appProductSku.entity.AppProductSku;
import com.dhlg.module.linxing.appProductSpecification.entity.AppProductSpecification;
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
@TableName("app_product")
public class AppProduct implements Serializable {

    private static final long serialVersionUID = 1L;

     /**
     *id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品标题
     */
    @TableField("title")
    private String title;
    /**
     * 商品简介
     */
    @TableField("description")
    private String description;
    /**
     * 商品封面
     */
    @TableField("cover_image")
    private String coverImage;
    /**
     * 轮播图
     */
    @TableField("images")
    private String images;
    /**
     * 详情图
     */
    @TableField("detail_images")
    private String detailImages;
    /**
     * 优先级
     */
    @TableField("priority")
    private Integer priority;
    /**
     * 销售量
     */
    @TableField("sold_count")
    private Integer soldCount;
    /**
     * 门店id
     */
    @TableField("store_id")
    private Integer storeId;
    /**
     * 创建人
     */
    @TableField("created_at")
    private String createdAt;
    /**
     * 商品状态
     */
    @TableField("status")
    private Integer status;
    /**
     * 商品种类
     */
    @TableField("category_id")
    private Integer categoryId;
    /**
     * 1购买商品 2兑换商品
     */
    @TableField("kind")
    private Integer kind;

    @TableField(exist=false)
    private List<AppProductSku> appProductSkuList;

    @TableField(exist=false)
    private List<AppProductSpecification> productSpecificationList;

}
