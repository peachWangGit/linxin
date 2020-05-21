package com.dhlg.utils.common.exception;


public class UncheckedException extends ParamIsNullException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误code，用于唯一标识错误类型
     */
    private String errorCode = null;

    private String errorBody = null;

    /**
     * 错误信息
     */
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 传递给变量的错误值
     */
    public UncheckedException() {

    }

    public UncheckedException(String errorCode, String body, String errorMessage) {
        this.errorCode = errorCode;
        this.errorBody = body;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "UncheckedException{" +
                "errorCode='" + errorCode + '\'' +
                ", errorBody='" + errorBody + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public UncheckedException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


    public String getErrorBody() {
        return errorBody;
    }

    public void setErrorBody(String errorBody) {
        this.errorBody = errorBody;
    }
}
