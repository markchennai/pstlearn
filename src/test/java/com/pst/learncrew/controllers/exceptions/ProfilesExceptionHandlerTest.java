package com.pst.learncrew.controllers.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import brave.Tracer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

@ExtendWith(MockitoExtension.class)
public class ProfilesExceptionHandlerTest {

  @InjectMocks ProfilesExceptionHandler profilesExceptionHandler;

  @Mock MessageSource messageSource;

  @Mock private Tracer tracer;

  @Test
  void test_handleMethodArgumentNotValid() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", "*/*");
    MethodArgumentNotValidException ex = Mockito.mock(MethodArgumentNotValidException.class);
    Mockito.lenient().when(ex.getMessage()).thenReturn("ERR");
    FieldError fe = new FieldError("field", "field", "ERR");
    List<FieldError> list = new ArrayList<>();
    list.add(fe);
    Mockito.lenient().when(ex.getFieldErrors()).thenReturn(list);

    ResponseEntity<Object> obj =
        profilesExceptionHandler.handleMethodArgumentNotValid(
            ex, headers, HttpStatusCode.valueOf(400), Mockito.mock(WebRequest.class));
    assertEquals(obj.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  void test_handleHttpMessageNotReadable() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", "*/*");
    HttpMessageNotReadableException ex = Mockito.mock(HttpMessageNotReadableException.class);

    ResponseEntity<Object> obj =
        profilesExceptionHandler.handleHttpMessageNotReadable(
            ex, headers, HttpStatusCode.valueOf(400), Mockito.mock(WebRequest.class));
    assertEquals(obj.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  void test_handleHttpMessageNotReadable_InvalidFormatException() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", "*/*");
    HttpMessageNotReadableException ex = Mockito.mock(HttpMessageNotReadableException.class);
    Mockito.when(ex.getCause()).thenReturn(Mockito.mock(InvalidFormatException.class));

    ResponseEntity<Object> obj =
        profilesExceptionHandler.handleHttpMessageNotReadable(
            ex, headers, HttpStatusCode.valueOf(400), Mockito.mock(WebRequest.class));
    assertEquals(obj.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  void test_handleHttpMessageNotReadable_MismatchedInputException() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", "*/*");
    HttpMessageNotReadableException ex = Mockito.mock(HttpMessageNotReadableException.class);
    Mockito.when(ex.getCause()).thenReturn(Mockito.mock(MismatchedInputException.class));

    ResponseEntity<Object> obj =
        profilesExceptionHandler.handleHttpMessageNotReadable(
            ex, headers, HttpStatusCode.valueOf(400), Mockito.mock(WebRequest.class));
    assertEquals(obj.getStatusCode(), HttpStatus.BAD_REQUEST);
  }
}
