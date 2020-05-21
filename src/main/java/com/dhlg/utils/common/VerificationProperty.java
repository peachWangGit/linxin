package com.dhlg.utils.common;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述
 * User:whfch
 * Date:2020/2/2
 * Time:22:08
 * 工具类校验字段重复
 */
@Component
public class VerificationProperty {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //判断该字段值是否重复
    public boolean lockAndVerify(Object targetObject, String fieldName, String requestId) {
        boolean isRepetition = false;      //字段值是否重复
        //获取表名
        String tableName = this.getTableName(targetObject);
        //获取字段值
        String fieldValue = this.getFieldValue(targetObject, fieldName);
        //获取id值
        String id=requestId;
        //去数据库校验字段值是否重复
        String querySql =
                "select count(1) as counts from " + tableName + " where " + fieldName + "=:fieldValue ";
        if (StringUtils.isNotEmpty(id)) {
            querySql += " and id != :id ";
        }

        Map<String, Object> params = new HashMap<>();
        params.put("tableName", tableName);
        params.put("lockField", fieldName);
        params.put("fieldValue", fieldValue);
        params.put("id", id);

        Integer integer = jdbcTemplate.queryForObject(querySql, params, Integer.class);

        if ( null != integer && integer > 0) {
            //字段值在数据表中已存在
            isRepetition = true;
        }

        return isRepetition;
    }

    //获取表名
    private String getTableName(Object targetObject) {
        Class<?> aClass = targetObject.getClass();
        return aClass.getAnnotation(TableName.class).value();
    }

    private String getFieldValue(Object targetObject, String lockField) {
        try {
            //字段的驼峰命名法与下划线风格命名法转换工具StringUtils.underscoreToCamelCase
            Field field = targetObject.getClass().getDeclaredField(StringUtils.underscoreToCamelCase(lockField));
            field.setAccessible(true);
            try {
                return (String) field.get(targetObject);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
