package com.trip.commons.core.exception;

/**
 * 非检测型业务异常
 *
 * @author fqh
 * @create 2016-12-08 23:21
 */
public class ServiceException extends RuntimeException implements ICodeExcetpion {

    private static final long serialVersionUID = -7866541811708711821L;

    /**
     * 错误代码
     */
    private String code;

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String code, Throwable t) {
        super(t);
        this.code = code;
    }

    public ServiceException(String code, String message, Throwable t) {
        super(message, t);
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
