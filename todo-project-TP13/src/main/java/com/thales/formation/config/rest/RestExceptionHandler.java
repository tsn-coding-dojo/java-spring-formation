package com.thales.formation.config.rest;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.thales.formation.enums.ErrorCode;
import com.thales.formation.exception.AbstractAppRuntimeException;
import com.thales.formation.exception.AppCustomException;
import com.thales.formation.exception.AppRuntimeException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @SuppressWarnings("rawtypes")
  @ExceptionHandler({ AppRuntimeException.class })
  public ResponseEntity handleAppRuntimeException(HttpServletRequest req, AppRuntimeException exception) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Manages Msid Runtime managed exceptions. The http code and response
   * object will be dynamic depending on the exception
   */
  @SuppressWarnings("rawtypes")
  @ExceptionHandler({ AbstractAppRuntimeException.class })
  public ResponseEntity handleMsidRuntimeException(HttpServletRequest req, AbstractAppRuntimeException exception) {
    ErrorCode errorCode = exception.getErrorCode();
    Object response = exception.getResponse();
    return getResponseEntity(errorCode, response);
  }

  /**
   * Handle Entity not found exception.<br>
   * Return a 404 HttpStatus to the client.
   */
  @SuppressWarnings("rawtypes")
  @ExceptionHandler({ EntityNotFoundException.class })
  public ResponseEntity handleError404(HttpServletRequest req, EntityNotFoundException exception) {
    return getResponseEntity(ErrorCode.NOT_FOUND, exception.getMessage());
  }

  /**
   * Handle database conflict exceptions (ConstraintViolation, OptimisticLock)
   * Return a 409 HttpStatus to the client.
   */
  @SuppressWarnings("rawtypes")
  @ExceptionHandler({ DataIntegrityViolationException.class, ObjectOptimisticLockingFailureException.class, HibernateOptimisticLockingFailureException.class })
  public ResponseEntity handleRetryableException(HttpServletRequest req, Exception exception) {
    return getResponseEntity(ErrorCode.CONFLICT, exception.getMessage());
  }

  //  /**
  //   * Handle internal server error.<br>
  //   * Return a 500 HttpStatus to the client.
  //   */
  //  @SuppressWarnings("rawtypes")
  //  @ExceptionHandler({ Exception.class })
  //  public ResponseEntity handleUnexpectedException(HttpServletRequest req, Exception exception) {
  //    return getResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR, null);
  //  }

  @ExceptionHandler({ AppCustomException.class })
  public ResponseEntity<ErrorObject> handleAppCustomException(HttpServletRequest req, AppCustomException exception) {
    return ResponseEntity.badRequest().body(new ErrorObject(exception.getMessage()));
  }

  @SuppressWarnings("rawtypes")
  @ExceptionHandler({ AccessDeniedException.class })
  public ResponseEntity handleError403(HttpServletRequest req, AccessDeniedException exception) {
    return getResponseEntity(ErrorCode.FORBIDDEN, null);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    return ResponseEntity.badRequest().body(new ErrorObject(ex.getMessage()));
  }

  private ResponseEntity<?> getResponseEntity(ErrorCode errorCode, Object response) {

    HttpHeaders headers = new HttpHeaders();
    headers.add("error", errorCode.name());

    if (response == null) {
      return new ResponseEntity<>(headers, errorCode.httpStatus());
    }

    return new ResponseEntity<>(response, headers, errorCode.httpStatus());

  }

  public class ErrorObject {

    String message;

    public ErrorObject() {
    }

    public ErrorObject(String message) {
      super();
      this.message = message;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

  }

}
