package com.pst.learncrew.controllers.exceptions;

import brave.Span;
import brave.Tracer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.pst.learncrew.models.Error;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProfilesExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired private Tracer tracer;

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {

    List<Error> errorList = new ArrayList<>();
    Locale locale = LocaleContextHolder.getLocale();
    String traceIdString = null;
    Span span = tracer.currentSpan();
    if (span != null) {
      traceIdString = span.context().traceIdString();
    }
    for (FieldError err : ex.getFieldErrors()) {
      Error error = new Error();
      error.setId(traceIdString);
      error.setCode("ERR400");
      error.setTitle(getMessageSource().getMessage("ERR400_REQUIRED.TITLE", null, locale));
      error.setDetail(
          getMessageSource()
              .getMessage(err.getDefaultMessage(), new Object[] {err.getField()}, locale));
      errorList.add(error);
    }
    return new ResponseEntity<>(errorList, headers, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    Locale locale = LocaleContextHolder.getLocale();
    String traceIdString = null;
    Span span = tracer.currentSpan();
    if (span != null) {
      traceIdString = span.context().traceIdString();
    }
    List<Error> errorList = new ArrayList<>();
    Error error = new Error();
    error.setId(traceIdString);
    error.setCode("ERR400");
    error.setTitle(getMessageSource().getMessage("ERR400_ENUM.TITLE", new Object[] {}, locale));
    if (ex.getCause() instanceof InvalidFormatException) {
      InvalidFormatException ifx = (InvalidFormatException) ex.getCause();
      if (ifx.getTargetType() != null && ifx.getTargetType().isEnum()) {
        String fieldName = ifx.getPath().get(ifx.getPath().size() - 1).getFieldName();
        error.setDetail(
            getMessageSource().getMessage("ERR400_ENUM", new Object[] {fieldName}, locale));
      }
    } else if (ex.getCause() instanceof MismatchedInputException) {
      MismatchedInputException mix = (MismatchedInputException) ex.getCause();
      if (mix.getTargetType() != null && mix.getTargetType().toGenericString().contains("List")) {
        String fieldName = mix.getPath().get(mix.getPath().size() - 1).getFieldName();
        error.setDetail(
            getMessageSource().getMessage("ERR400_LIST", new Object[] {fieldName}, locale));
      }
    } else {
      error.setDetail(
          getMessageSource().getMessage("ERR400_INVALID", new Object[] {"REQ_BODY"}, locale));
    }

    errorList.add(error);
    return new ResponseEntity<>(errorList, headers, HttpStatus.BAD_REQUEST);
  }
}
