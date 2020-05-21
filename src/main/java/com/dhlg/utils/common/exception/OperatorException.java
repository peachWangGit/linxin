package com.dhlg.utils.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 描述 curd类
 * User:whfch
 * Date:2020/3/11
 * Time:23:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OperatorException extends Exception {

    private String type;

    private String data;

    public OperatorException(String type) {
        this.type = type;
    }
    public OperatorException(String type,String data) {
        this.type = type;
        this.data = data;
    }
}
