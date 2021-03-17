package com.thales.formation.exception;

import com.thales.formation.enums.ErrorCode;

@SuppressWarnings("serial")
public class AppNotFoundException extends AbstractAppRuntimeException {

	@Override
	public ErrorCode getErrorCode() {
		return ErrorCode.NOT_FOUND;
	}

	public AppNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppNotFoundException(String message) {
		super(message);
	}

	public AppNotFoundException(Throwable cause) {
		super(cause);
	}

}
