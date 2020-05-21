package com.dhlg.module.system.sysLoginLog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xu
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_login_log")
public class SysLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id,记录登录用户
     */
    @TableField("user_id")
    private String userId;

    /**
     * 登录ip
     */
    @TableField("login_ip")
    private String loginIp;

    /**
     * 登录地点
     */
    @TableField("login_address")
    private String loginAddress;

    /**
     * 浏览器名称
     */
    @TableField("browser_name")
    private String browserName;

    /**
     * 操作系统
     */
    @TableField("operation_system")
    private String operationSystem;

    /**
     * 登录状态
     */
    @TableField("login_status")
    private String loginStatus;

    /**
     * 操作信息
     */
    @TableField("operation_info")
    private String operationInfo;

    /**
     * 登录时间
     */
    @TableField("login_time")
    private Date loginTime;

    @TableField(value = "userName", exist = false)
    private String userName;



    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String LOGIN_IP = "login_ip";

    public static final String LOGIN_ADDRESS = "login_address";

    public static final String BROWSER_NAME = "browser_name";

    public static final String OPERATION_SYSTEM = "operation_system";

    public static final String LOGIN_STATUS = "login_status";

    public static final String OPERATION_INFO = "operation_info";

    public static final String LOGIN_TIME = "login_time";

}
