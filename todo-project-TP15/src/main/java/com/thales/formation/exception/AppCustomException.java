package com.thales.formation.exception;

@SuppressWarnings("serial")
// But we link our exception to our framework to application service code
//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AppCustomException extends RuntimeException {

  public AppCustomException() {
    super();
  }

  public AppCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public AppCustomException(String message, Throwable cause) {
    super(message, cause);
  }

  public AppCustomException(String message) {
    super(message);
  }

  public AppCustomException(Throwable cause) {
    super(cause);
  }

}
