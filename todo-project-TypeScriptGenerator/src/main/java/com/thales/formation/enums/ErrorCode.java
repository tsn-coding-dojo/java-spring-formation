package com.thales.formation.enums;

import org.springframework.http.HttpStatus;

import com.thales.formation.exception.AppBadRequestException;
import com.thales.formation.exception.AppConflictException;
import com.thales.formation.exception.AppForbiddenException;
import com.thales.formation.exception.AppNotFoundException;
import com.thales.formation.exception.AppPreconditionFailedException;
import com.thales.formation.exception.AppUnauthorizedException;

public enum ErrorCode {

	// FORBIDDEN

	FORBIDDEN(HttpStatus.FORBIDDEN, AppForbiddenException.class), //

	// UNAUTHORIZED

	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, AppUnauthorizedException.class), //

	// BAD REQUEST

	BAD_REQUEST(HttpStatus.BAD_REQUEST, AppBadRequestException.class), //

	// NOT FOUND

	NOT_FOUND(HttpStatus.NOT_FOUND, AppNotFoundException.class), //

	// CONFLICT

	CONFLICT(HttpStatus.CONFLICT, AppConflictException.class), //

	// INTERNAL SERVER ERROR

	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, RuntimeException.class), //

	// PRECONDITION FAILED

	PRECONDITION_FAILED(HttpStatus.PRECONDITION_FAILED, AppPreconditionFailedException.class), //

	// SERVICE UNAVAILABLE

	/**
	 * This error is only here to make it accessible in Angular (if the service
	 * is unavailable, it is not able to generate it... :-)
	 */
	SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, null);

	private final HttpStatus httpStatus;
	private final Class exceptionClass;

	private ErrorCode(HttpStatus httpStatus, Class exceptionClass) {
		this.httpStatus = httpStatus;
		this.exceptionClass = exceptionClass;
	}

	public HttpStatus httpStatus() {
		return this.httpStatus;
	}

	public Class exceptionClass() {
		return this.exceptionClass;
	}

}
