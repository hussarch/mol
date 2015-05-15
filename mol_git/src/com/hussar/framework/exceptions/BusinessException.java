package com.hussar.framework.exceptions;


/**
 * @BusinessException.java
 * @author XiaoYi(hussarch@126.com) Created on 2014-12-28, ©2014 some rights
 *         reserved
 */
public class BusinessException extends RuntimeException {

	private ErrorType errorCode;
	
	public BusinessException(){
		super();
	}

	public BusinessException(ErrorType errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(ErrorType errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public BusinessException(ErrorType errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public BusinessException(ErrorType errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public ErrorType getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorType errorCode) {
		this.errorCode = errorCode;
	}
}
