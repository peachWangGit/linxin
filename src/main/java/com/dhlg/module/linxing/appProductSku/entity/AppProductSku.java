package com.dhlg.module.linxing.appProductSku.entity;

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
@TableName("app_product_sku")
public class AppProductSku implements Serializable {

    private static final long serialVersionUID = 1L;

     /**
     *id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 快递费
     */
    @TableField("courier_price")
    private Integer courierPrice;
    /**
     * 创建人
     */
    @TableField("created_at")
    private String createdAt;
    /**
     * 当前价
     */
    @TableField("current_price")
    private Integer currentPrice;
    /**
     * 剩余数量
     */
    @TableField("inventory")
    private Integer inventory;
    /**
     * 主打产品
     */
    @TableField("main_sku")
    private Integer mainSku;
    /**
     * 原价
     */
    @TableField("original_price")
    private Integer originalPrice;
    /**
     * 关联的商品
     */
    @TableField("product_id")
    private Integer productId;
    /**
     * 购买所需积分
     */
    @TableField("redeem_integral")
    private Integer redeemIntegral;
    /**
     * 商品返还积分   如果是兑换的商品则不返还积分
     */
    @TableField("return_integral")
    private Integer returnIntegral;
    /**
     * 商品图片
     */
    @TableField("sku_image")
    private String skuImage;
    /**
     * sku标题
     */
    @TableField("sku_title")
    private String skuTitle;
    /**
     * 商品状态，如上架、售尽、热销、下架等
     */
    @TableField("status")
    private Integer status;
    /**
     * 单位
     */
    @TableField("unit")
    private String unit;

}
