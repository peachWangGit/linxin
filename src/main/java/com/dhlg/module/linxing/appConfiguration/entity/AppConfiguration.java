package com.dhlg.module.linxing.appConfiguration.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xu
 * @since 2020-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AppConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 配置名称
     */
    @TableField("configuration_name")
    private String configurationName;

    /**
     * 对应的值
     */
    @TableField("configuration_value")
    private String configurationValue;


    public static final String ID = "id";

    public static final String CONFIGURATION_NAME = "configuration_name";

    public static final String CONFIGURATION_VALUE = "configuration_value";

}
