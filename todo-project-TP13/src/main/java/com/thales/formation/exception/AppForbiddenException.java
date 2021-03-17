package com.thales.formation.exception;

import com.thales.formation.enums.ErrorCode;

@SuppressWarnings("serial")
public class AppForbiddenException extends AbstractAppRuntimeException {

	@Override
	public ErrorCode getErrorCode() {
		return ErrorCode.FORBIDDEN;
	}

	public AppForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppForbiddenException(String message) {
		super(message);
	}

	public AppForbiddenException(Throwable cause) {
		super(cause);
	}

}
