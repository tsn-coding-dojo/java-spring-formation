package com.thales.formation.exception;

import com.thales.formation.enums.ErrorCode;

@SuppressWarnings("serial")
public class AppInternalServerErrorException extends AbstractAppRuntimeException {

	@Override
	public ErrorCode getErrorCode() {
		return ErrorCode.INTERNAL_SERVER_ERROR;
	}

	public AppInternalServerErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppInternalServerErrorException(String message) {
		super(message);
	}

	public AppInternalServerErrorException(Throwable cause) {
		super(cause);
	}

}
