package com.dhlg.module.system.sysButton.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xu
 * @since 2020-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysButton对象", description="")
@TableName("sys_button")
public class SysButton implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 按钮id主键
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
     * 所属菜单id
     */
    @TableField("menu_id")
    private String menuId;

    /**
     * 按钮接口权限名称
     */
    @TableField("button_url")
    private String buttonUrl;

    /**
     * 状态
     */
    @TableField("status")
    private String status;
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

    public static final String MENU_ID = "menu_id";

    public static final String DESCRIPTION = "description";

    public static final String CREATE_USER = "create_user";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_USER = "update_user";

    public static final String UPDATE_TIME = "update_time";

}
