package com.jcm.portfolio.utils.exception;

import lombok.Data;

@Data
public class ExceptionMessageModel {

  private String errorCode;
  private String errorMessage;
  private String errorDevMessage;

}
