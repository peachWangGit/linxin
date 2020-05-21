package com.dhlg.utils.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.Map;

/**
 * 前端分页请求的封装类
 */
@Data
public class Parameter {
    /**
     * 当前页
     */
    private Integer number = 1;
    /**
     * 当前页数据量大小
     */
    private Integer size = 10;

    /**
     * 条件的map集合
     */
    private Map<String, String> condition;

    private Map<String, Object> condition2;

    public Page getPage() {
        return new Page(this.number, this.size);
    }


    //默认当前页数
    public static Integer defaultCurrentPage = 1;

    //默认页大小
    public static Integer defaultPageSize = 10;

    public void setDefault(){
        if (null == this.getNumber() || this.getNumber() == 0 ||"".equals(this.getNumber())) {
            this.setNumber(Parameter.defaultCurrentPage);
        }
        if (null == this.getSize() || this.getSize() == 0 ||"".equals(this.getSize())) {
            this.setSize(Parameter.defaultPageSize);
        }
    }
}
