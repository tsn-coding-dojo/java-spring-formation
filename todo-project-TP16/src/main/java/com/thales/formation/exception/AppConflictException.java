package com.thales.formation.exception;

import com.thales.formation.enums.ErrorCode;

@SuppressWarnings("serial")
public class AppConflictException extends AbstractAppRuntimeException {

	@Override
	public ErrorCode getErrorCode() {
		return ErrorCode.CONFLICT;
	}

	public AppConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppConflictException(String message) {
		super(message);
	}

	public AppConflictException(Throwable cause) {
		super(cause);
	}

}
