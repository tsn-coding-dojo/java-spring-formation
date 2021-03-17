package com.thales.formation.exception;

import com.thales.formation.enums.ErrorCode;

@SuppressWarnings("serial")
public class AppUnauthorizedException extends AbstractAppRuntimeException {

	@Override
	public ErrorCode getErrorCode() {
		return ErrorCode.UNAUTHORIZED;
	}

	public AppUnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppUnauthorizedException(String message) {
		super(message);
	}

	public AppUnauthorizedException(Throwable cause) {
		super(cause);
	}

}
