package com.thales.formation.exception;

@SuppressWarnings("serial")
public class AppRuntimeException extends AppInternalServerErrorException {

	public AppRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppRuntimeException(String message) {
		super(message);
	}

	public AppRuntimeException(Throwable cause) {
		super(cause);
	}

}
