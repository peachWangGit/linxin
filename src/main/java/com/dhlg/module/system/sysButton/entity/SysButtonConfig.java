package com.dhlg.module.system.sysButton.entity;

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
 * @since 2020-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_button_config")
public class SysButtonConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 按钮id
     */
    @TableId("id")
    private String id;

    /**
     * 按钮编码
     */
    @TableField("button_code")
    private String buttonCode;

    /**
     * 按钮名称
     */
    @TableField("button_name")
    private String buttonName;

    /**
     * 按钮描述
     */
    @TableField("description")
    private String description;

    /**
     * 创建用户
     */
    @TableField("create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新用户
     */
    @TableField("update_user")
    private String updateUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    public static final String ID = "id";

    public static final String BUTTON_CODE = "button_code";

    public static final String BUTTON_NAME = "button_name";

    public static final String DESCRIPTION = "description";

    public static final String CREATE_USER = "create_user";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_USER = "update_user";

    public static final String UPDATE_TIME = "update_time";

}
