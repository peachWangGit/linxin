package com.dhlg.module.linxing.appCooperation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

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
 * @since 2020-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AppCooperation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * user_id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 姓名
     */
    @TableField("hisName")
    private String hisName;

    /**
     * 手机
     */
    @TableField("phone")
    private String phone;

    /**
     * 商家地址
     */
    @TableField("address")
    private String address;

    /**
     * 申请时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("application_time")
    private Date applicationTime;

    /**
     * 状态 1.未读   2.已读
     */
    @TableField("status")
    private Integer status;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String HISNAME = "hisName";

    public static final String PHONE = "phone";

    public static final String ADDRESS = "address";

    public static final String APPLICATION_TIME = "application_time";

    public static final String STATUS = "status";

}
