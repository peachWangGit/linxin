package com.dhlg.module.linxing.appCard.entity;

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
 * @since 2020-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AppCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 卡名
     */
    @TableField("card_name")
    private String cardName;

    /**
     * 卡片照片
     */
    @TableField("card_image")
    private String cardImage;

    /**
     * 价格
     */
    @TableField("price")
    private Integer price;

    /**
     * 活动价
     */
    @TableField("discount_price")
    private Integer discountPrice;

    /**
     * 介绍
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 赠送积分
     */
    @TableField("give_integral")
    private Integer giveIntegral;

    /**
     * 购房优惠
     */
    @TableField("purchase_discount")
    private Integer purchaseDiscount;

    public static final String ID = "id";

    public static final String CARD_NAME = "card_name";

    public static final String CARD_IMAGE = "card_image";

    public static final String PRICE = "price";

    public static final String DISCOUNT_PRICE = "discount_price";

    public static final String INTRODUCTION = "introduction";

}
