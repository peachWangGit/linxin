package com.dhlg.module.linxing.appOrderItem.entity;

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
@TableName("app_order_item")
public class AppOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

     /**
     *id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 购买时的价格
     */
    @TableField("buy_price")
    private Integer buyPrice;
    /**
     * 订单编号
     */
    @TableField("order_sn")
    private String orderSn;
    /**
     * 关联的商品
     */
    @TableField("product_id")
    private Integer productId;
    /**
     * 商品图片
     */
    @TableField("product_image")
    private String productImage;
    /**
     * 商品标题
     */
    @TableField("product_title")
    private String productTitle;
    /**
     * 数量
     */
    @TableField("quantity")
    private Integer quantity;
    /**
     * 兑换时的积分
     */
    @TableField("redeem_integral")
    private Integer redeemIntegral;
    /**
     * 关联的商品sku
     */
    @TableField("sku_id")
    private Integer skuId;

}
