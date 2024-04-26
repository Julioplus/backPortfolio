package com.jcm.portfolio.utils.exception;

public class CustomException extends RuntimeException {

  public CustomException(String errorMessage) {
    super(errorMessage);
  }

  public CustomException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }

}
