package com.trip.commons.core.exception;

/**
 * 检测型业务异常
 *
 * @author fqh
 * @create 2016-12-08 23:21
 */
public class BusinessException extends Exception implements ICodeExcetpion {

    private static final long serialVersionUID = -244328073238511347L;

    /**
     * 错误代码
     */
    private String code;

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String code, Throwable t) {
        super(t);
        this.code = code;
    }

    public BusinessException(String code, String message, Throwable t) {
        super(message, t);
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
