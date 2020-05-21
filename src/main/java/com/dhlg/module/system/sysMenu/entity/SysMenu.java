package com.dhlg.module.system.sysMenu.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dhlg.module.system.sysButton.entity.SysButton;
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
 * @since 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysMenu对象", description="")
@TableName("sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private String id;

    /**
     * 编码
     */
    @TableField("menu_code")
    private String menuCode;

    /**
     * 名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 父级主键
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 路径
     */
    @TableField("url")
    private String url;

    /**
     * 状态(是否启用 0禁用 1启用)
     */
    @TableField("status")
    private String status;

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
     * 修改人
     */
    @TableField("update_user")
    private String updateUser;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * 全选表用到的占位字段
     */
    @TableField("sys_type")
    private String sysType;

    /**
     * 是否含有下级
     */
    @TableField("is_children")
    private Integer isChildren;

    @TableField(value = "children", exist = false) // 标记为非数据库字段
    private List<SysMenu> children;


    @TableField(value = "button", exist = false) // 标记为非数据库字段
    private List<SysButton> buttonList;

    @TableField(value = "menu_code_name", exist = false) // 标记为非数据库字段
    private String menuCodeName;



    public static final String ID = "id";

    public static final String MENU_CODE = "menu_code";

    public static final String MENU_NAME = "menu_name";

    public static final String ICON = "icon";

    public static final String PARENT_ID = "parent_id";

    public static final String URL = "url";

    public static final String STATUS = "status";

    public static final String CREATE_USER = "create_user";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_USER = "update_user";

    public static final String UPDATE_TIME = "update_time";

    public static final String DESCRIPTION = "description";

    public static final String SYS_TYPE = "sys_type";

    public static final String IS_CHILDREN = "is_children";

}
