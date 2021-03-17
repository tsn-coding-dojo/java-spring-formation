package com.thales.formation.exception;

@SuppressWarnings("serial")
public class AppRetryeableRuntimeException extends AppRuntimeException {

	public AppRetryeableRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppRetryeableRuntimeException(String message) {
		super(message);
	}

	public AppRetryeableRuntimeException(Throwable cause) {
		super(cause);
	}

}
