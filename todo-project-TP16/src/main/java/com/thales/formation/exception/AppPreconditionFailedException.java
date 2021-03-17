package com.thales.formation.exception;

import com.thales.formation.enums.ErrorCode;

@SuppressWarnings("serial")
public class AppPreconditionFailedException extends AbstractAppRuntimeException {

	@Override
	public ErrorCode getErrorCode() {
		return ErrorCode.PRECONDITION_FAILED;
	}

	public AppPreconditionFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppPreconditionFailedException(String message) {
		super(message);
	}

	public AppPreconditionFailedException(Throwable cause) {
		super(cause);
	}

}
