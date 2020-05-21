package com.dhlg.module.linxing.appOrder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.dhlg.module.linxing.appOrderItem.entity.AppOrderItem;
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
@TableName("app_order")
public class AppOrder implements Serializable {

    private static final long serialVersionUID = 1L;

     /**
     *id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 地址
     */
    @TableField("address")
    private String address;
    /**
     * 1积分订单2普通订单
     */
    @TableField("category")
    private Integer category;
    /**
     * 市
     */
    @TableField("city")
    private String city;
    /**
     * 收货人
     */
    @TableField("contact")
    private String contact;
    /**
     * 区
     */
    @TableField("county")
    private String county;
    /**
     * 快递公司
     */
    @TableField("courier_company")
    private String courierCompany;
    /**
     * 快递单号
     */
    @TableField("courier_number")
    private String courierNumber;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("creat_time")
    private Date creatTime;
    /**
     * 自取件取件token
     */
    @TableField("get_token")
    private String getToken;
    /**
     * 1自取2快递
     */
    @TableField("kind")
    private Integer kind;
    /**
     * 订单编号
     */
    @TableField("order_sn")
    private String orderSn;
    /**
     * 订单状态
     */
    @TableField("order_status")
    private Integer orderStatus;
    /**
     * 付款流水id
     */
    @TableField("pay_id")
    private String payId;
    /**
     * 支付积分
     */
    @TableField("pay_integral")
    private Integer payIntegral;
    /**
     * 支付金额
     */
    @TableField("pay_price")
    private Integer payPrice;
    /**
     * 支付时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("pay_time")
    private Date payTime;
    /**
     * 联系电话
     */
    @TableField("phone")
    private String phone;
    /**
     * 邮费
     */
    @TableField("postage")
    private Integer postage;
    /**
     * 省
     */
    @TableField("province")
    private String province;
    /**
     * 收货时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("receipt_time")
    private Date receiptTime;
    /**
     * 店铺id
     */
    @TableField("store_id")
    private Integer storeId;
    /**
     * token时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("token_time")
    private Date tokenTime;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;


    @TableField(exist=false)
    private List<AppOrderItem> appOrderItemList;


}
