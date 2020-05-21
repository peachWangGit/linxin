package com.dhlg.utils.common.exception;

/**
 * 描述
 * User:whfch
 * Date:2020/2/19
 * Time:10:58
 */

import com.dhlg.utils.common.Result;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * 处理全局异常的公共类
 */
@RestControllerAdvice
public class ExceptionHandlerUtils {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerUtils.class);

    //访问没有权限的资源
    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(AuthorizationException e, HttpServletRequest request) {

        return new Result("401", "抱歉，您没有权限访问该资源！", "抱歉，您没有权限访问该资源！");
    }
    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public Object handleNullPointerException(NullPointerException e, HttpServletRequest request) {
        e.printStackTrace();
        return new Result("500", "空指针错误！", "空指针错误！");
    }
    //没有接收到应有的请求参数
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        e.printStackTrace();
        return new Result("500", "抱歉，没有接收到应有的请求参数！", "抱歉，没有接收到应有的请求参数！");
    }

    //sql错误
    @ExceptionHandler(SQLException.class)
    public Object handleSQLException(SQLException e, HttpServletRequest request) {
        e.printStackTrace();
        return new Result("500", "sql执行出现异常！", "sql执行出现异常！");
    }

    //请求不支持
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object handleRequestNotSupported(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        return new Result("500", "不支持本次请求方式！", e.getMessage());
    }

    //CRUD操作失败
    @ExceptionHandler(OperatorException.class)
    public Object handleOperatorException(OperatorException e, HttpServletRequest request) {
        logger.info("错误操作--" + e.getType());
        return new Result("400", e.getType(), e.getData() == null ? "操作失败" : e.getData());
    }

    //接口必须的参数接收为Null
    @ExceptionHandler(ParamIsNullException.class)
    public Object handleParameterIsNullException(ParamIsNullException e, HttpServletRequest request) {
        e.printStackTrace();
        return new Result("400", "没有接收到请求参数！", "没有接收到请求参数");
    }
    //为空
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public Object ParamIsNullException(Exception e, HttpServletRequest request) {
//        e.printStackTrace();
//        return new Result("500", "参数为空", e.getMessage());
//    }
}
