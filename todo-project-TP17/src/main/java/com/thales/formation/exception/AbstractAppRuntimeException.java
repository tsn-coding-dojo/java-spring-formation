package com.thales.formation.exception;

import com.thales.formation.enums.ErrorCode;

@SuppressWarnings("serial")
public abstract class AbstractAppRuntimeException extends RuntimeException {

	public abstract ErrorCode getErrorCode();

	/**
	 * Http response object
	 */
	public Object getResponse() {
		return super.getMessage();
	}

	public AbstractAppRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public AbstractAppRuntimeException(String message) {
		super(message);
	}

	public AbstractAppRuntimeException(Throwable cause) {
		super(cause);
	}

}
