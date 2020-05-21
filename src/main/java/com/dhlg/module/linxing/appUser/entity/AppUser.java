package com.dhlg.module.linxing.appUser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.dhlg.module.linxing.appUserintegral.entity.AppUserintegral;
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
@TableName("app_user")
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 头像
     */
    @TableField("avatarUrl")
    private String avatarUrl;
    /**
     * 余额
     */
    @TableField("balance")
    private Integer balance;
    /**
     * 创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("create_date")
    private Date createDate;
    /**
     * 一级代理收成比例
     */
    @TableField("first_level_proxy_ratio")
    private Float firstLevelProxyRatio;
    /**
     * 现在可用收益
     */
    @TableField("freeze_income")
    private Integer freezeIncome;
    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 身份证
     */
    @TableField("id_card")
    private String idCard;
    /**
     * 员工身份
     */
    @TableField("identity")
    private Integer identity;
    /**
     * 积分
     */
    @TableField("integral")
    private Integer integral;
    /**
     * 邀请数
     */
    @TableField("invite_amount")
    private Integer inviteAmount;
    /**
     * 上次登录时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("last_login_date")
    private Date lastLoginDate;
    /**
     * 上次登录ip
     */
    @TableField("last_login_ip")
    private String lastLoginIp;
    /**
     * 会员等级 0普通用户 1乐享居民  2乐享创业人  3乐享城主  4乐享合伙人
     */
    @TableField("level")
    private Integer level;
    /**
     * 昵称
     */
    @TableField("nickName")
    private String nickName;
    /**
     * 现在可用收益
     */
    @TableField("now_income")
    private Integer nowIncome;
    /**
     * openid
     */
    @TableField("openid")
    private String openid;
    /**
     * 手机
     */
    @TableField("phone")
    private String phone;
    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 二级代理收成比例
     */
    @TableField("second_level_proxy_ratio")
    private Float secondLevelProxyRatio;
    /**
     * 状态
     */
    @TableField("status")
    private Integer status;
    /**
     * 上级
     */
    @TableField("superior")
    private Integer superior;
    /**
     * 总收益
     */
    @TableField("total_income")
    private Integer totalIncome;

    @TableField(exist=false)
    private AppUserintegral userintegral;
}
