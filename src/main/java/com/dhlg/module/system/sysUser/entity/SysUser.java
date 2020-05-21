package com.dhlg.module.system.sysUser.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Lob;

/**
 * <p>
 * 
 * </p>
 *
 * @author xu
 * @since 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;


    /**
     * 密码
     */
    @TableField("password")
    private String password;



    /**
     * 权限id
     */
    @TableField("role_ids")
    private String roleIds;


    /**
     * 登录名
     */
    @TableField("login_user")
    private String loginUser;

    /**
     * 登录名
     */
    @TableField("status")
    private String status;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 部门
     */
    @TableField("department_id")
    private String departmentId;

    /**
     * 头像
     */
    @TableField("head_portrait")
    @Lob
    private byte[] headPortrait;

    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField("update_user")
    private String updateUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    @TableField(exist=false)
    private String headPortraitSrc;

    public static final String ID = "id";

    public static final String USER_NAME = "user_name";

    public static final String PASSWORD = "password";

    public static final String ROLEIDS = "role_ids";

    public static final String LOGIN_USER = "login_user";

    public static final String STATUS = "status";

    public static final String DESCRIPTION = "description";

    public static final String HEAD_PORTRAIT = "head_portrait";

    public static final String CREATE_USER = "create_user";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_USER = "update_user";

    public static final String UPDATE_TIME = "update_time";

}
