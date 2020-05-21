package com.dhlg.module.system.sysdic.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dic_type")
public class SysDicType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableField("dic_type")
    private String dicType;

    @TableField("status")
    private String status;

    @TableField("description")
    private String description;

    @TableField(value = "create_user")
    private String createUser;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_user")
    private String updateUser;

    @TableField(value = "update_time")
    private Date updateTime;



    public static final String ID = "id";

    public static final String DIC_TYPE = "dic_type";

    public static final String STATUS = "status";

    public static final String DESCRIPTION = "description";

}
