package com.jcm.portfolio.utils.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dev.morphia.mapping.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  public RestResponseEntityExceptionHandler() {
    super();
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, WebRequest request) {
    final String bodyOfResponse;
    try {
      bodyOfResponse = setExceptionModel(ex.getMessage(), request.getDescription(false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    // ex.getCause() instanceof JsonMappingException, JsonParseException // for additional information later on
    return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex, @NotNull HttpHeaders headers,
      @NotNull HttpStatusCode status,
      WebRequest request) {
    final String bodyOfResponse;
    try {
      bodyOfResponse = setExceptionModel(ex.getMessage(), request.getDescription(false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final
  WebRequest request) throws JsonProcessingException {
    final String bodyOfResponse = setExceptionModel(ex.getMessage(), request.getDescription(false));
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST,
        request);
  }

  @ExceptionHandler({DataIntegrityViolationException.class})
  public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex,
      final WebRequest request) throws JsonProcessingException {
    final String bodyOfResponse = setExceptionModel(ex.getMessage(), request.getDescription(false));
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST,
        request);
  }

  // 404

//  @ExceptionHandler(value = {EntityNotFoundException.class, MyResourceNotFoundException.class})
//  protected ResponseEntity<Object> handleNotFound(final RuntimeException ex,
//      final WebRequest request) {
//    final String bodyOfResponse = "This should be application specific";
//    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND,
//        request);
//  }

  // 409

  @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class})
  protected ResponseEntity<Object> handleConflict(final RuntimeException ex,
      final WebRequest request) throws JsonProcessingException {
    final String bodyOfResponse = setExceptionModel(ex.getMessage(), request.getDescription(false));
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT,
        request);
  }

  // 412

  // 500

  @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class,
      IllegalStateException.class})
  public ResponseEntity<Object> handleInternal(final RuntimeException ex,
      final WebRequest request) throws JsonProcessingException {
    log.error("500 Status Code", ex);
    final String bodyOfResponse = setExceptionModel(ex.getMessage(), request.getDescription(false));
    log.info(ex.getMessage());
    log.info(request.toString());

    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
        HttpStatus.INTERNAL_SERVER_ERROR, request);
  }


  private String setExceptionModel(String exceptionMessage, String requestString)
      throws JsonProcessingException {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    ExceptionMessageModel exceptionModel = new ExceptionMessageModel();
    
    exceptionModel.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    exceptionModel.setErrorMessage("Error en el servidor al llamar " + requestString);
    exceptionModel.setErrorDevMessage(
        "Exception Error: " + exceptionMessage + " al llamar a la URL: " + requestString);

    return ow.writeValueAsString(exceptionModel);
  }


}
