package com.dhlg.module.system.sysFile.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xu
 * @since 2020-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_files")
public class SysFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableField("url")
    private String url;

    @TableField("type")
    private String type;

    @TableField("status")
    private String status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("create_time")
    private Date createTime;

    @TableField("create_user")
    private String createUser;


    public static final String ID = "id";

    public static final String URL = "url";

    public static final String TYPE = "type";

    public static final String STATUS = "status";

    public static final String CREATE_TIME = "create_time";

    public static final String CREATE_USER = "create_user";

}
