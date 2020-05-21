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
@TableName("sys_dic")
public class SysDic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableField("dic_label")
    private String dicLabel;

    @TableField("dic_value")
    private String dicValue;

    @TableField("status")
    private String status;

    @TableField("description")
    private String description;

    @TableField("dic_type_id")
    private String dicTypeId;

    @TableField(value = "create_user")
    private String createUser;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_user")
    private String updateUser;

    @TableField(value = "update_time")
    private Date updateTime;


    @TableField(value = "dic_type", exist = false)
    private String dicType;



    public static final String ID = "id";

    public static final String DIC_LABEL = "dic_label";

    public static final String DIC_VALUE = "dic_value";

    public static final String STATUS = "status";

    public static final String DESCRIPTION = "description";

    public static final String DIC_TYPE_ID = "dic_type_id";

    public static final String DIC_TYPE = "dic_type";

}
