package com.thales.formation.exception;

import com.thales.formation.enums.ErrorCode;

@SuppressWarnings("serial")
public class AppBadRequestException extends AbstractAppRuntimeException {

	@Override
	public ErrorCode getErrorCode() {
		return ErrorCode.BAD_REQUEST;
	}

	public AppBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppBadRequestException(String message) {
		super(message);
	}

	public AppBadRequestException(Throwable cause) {
		super(cause);
	}

}
