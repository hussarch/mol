package com.hussar.framework.exceptions;

/**
 * @InvalidParamException.java
 * @author XiaoYi(hussarch@126.com) Created on 下午11:28:33 2014-8-27 ©2014, some
 *         rights reserved
 */
public class InvalidParamException extends RuntimeException {

	private ErrorType errorCode;
	
	public InvalidParamException(ErrorType errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public InvalidParamException(ErrorType errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public InvalidParamException(ErrorType errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public InvalidParamException(ErrorType errorCode, Throwable cause) {
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
