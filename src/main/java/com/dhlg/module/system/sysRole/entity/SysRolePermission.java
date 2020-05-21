package com.dhlg.module.system.sysRole.entity;

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
 * @since 2020-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_permission")
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色权限表id
     */
    @TableId("id")
    private String id;

    /**
     * 角色id
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 菜单id
     */
    @TableField("menu_id")
    private String menuId;

    /**
     * 按钮id
     */
    @TableField("button_id")
    private String buttonId;

    /**
     * 父节点id
     */
    @TableField("parent_id")
    private String parentId;


    public static final String ID = "id";

    public static final String ROLE_ID = "role_id";

    public static final String MENU_ID = "menu_id";

    public static final String BUTTON_ID = "button_id";

    public static final String PARENT_ID = "parent_id";

}
