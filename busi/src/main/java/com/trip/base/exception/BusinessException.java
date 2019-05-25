package com.trip.base.exception;

public class BusinessException extends RuntimeException {
	private String errorCode = "-1";
	private String errorMsg = "系统忙";
	public BusinessException(String errorCode, String errorMsg, Throwable e) {
		super(e);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public BusinessException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public BusinessException(Throwable e) {
		super(e);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}

