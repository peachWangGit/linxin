package com.dhlg.module.linxing.appHotelreservation.entity;

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
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("app_hotelreservation")
public class AppHotelreservation implements Serializable {

    private static final long serialVersionUID = 1L;

     /**
     *id
     */
     @TableId("id")
     private String id;


    /**
     * 酒店id
     */
    @TableField("hotel_id")
    private Integer hotelId;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 达到时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("arrival_time")
    private Date arrivalTime;
    /**
     * 预约时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("reservation_time")
    private Date reservationTime;
    /**
     * 预约人数
     */
    @TableField("amount")
    private Integer amount;
    /**
     * 姓名
     */
    @TableField("hisName")
    private String hisName;
    /**
     * 电话
     */
    @TableField("phone")
    private String phone;
    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;
    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

}
