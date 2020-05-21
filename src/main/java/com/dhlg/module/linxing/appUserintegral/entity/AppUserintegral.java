package com.dhlg.module.linxing.appUserintegral.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("app_userintegral")
public class AppUserintegral implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 交易数量
     */
    @TableField("amount")
    private Integer amount;
    /**
     * 过期时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("expired_time")
    private Date expiredTime;
    /**
     * 得到描述
     */
    @TableField("get_description")
    private String getDescription;
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 失去描述
     */
    @TableField("lose_description")
    private String loseDescription;
    /**
     * 状态   1待结算  2已结算  3已使用  4已过期
     */
    @TableField("status")
    private Integer status;
    /**
     * 交易时间 （不可用积分）
     */
     @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("transaction_time")
    private Date transactionTime;
    /**
     * 解冻时间 （变成可使用积分）
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("unfreeze_time")
    private Date unfreezeTime;
    /**
     * 使用时间 （变成可使用积分）
     */

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("use_time")
    private Date useTime;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 最后积分
     */
    @TableField("last_amount")
    private Integer lastAmount;
}
