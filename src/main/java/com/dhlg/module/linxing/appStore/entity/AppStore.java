package com.dhlg.module.linxing.appStore.entity;

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
@TableName("app_store")
public class AppStore implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 店名
     */
    @TableField("store_name")
    private String storeName;
    /**
     * 门店地址
     */
    @TableField("address")
    private String address;
    /**
     * 门店坐标经度
     */
    @TableField("longitude")
    private BigDecimal longitude;
    /**
     * 门店坐标纬度
     */
    @TableField("latitude")
    private BigDecimal latitude;
    /**
     * 门店电话
     */
    @TableField("phone")
    private String phone;
    /**
     * 门店logo
     */
    @TableField("logo")
    private String logo;
    /**
     * 门店介绍
     */
    @TableField("introduction")
    private String introduction;
    /**
     * 门店营业时间
     */
    @TableField("business_hours")
    private String businessHours;
    /**
     * 门店拥有者
     */
    @TableField("owner_id")
    private Integer ownerId;
    /**
     * 门店状态
     */
    @TableField("status")
    private Integer status;

}
