package com.hotelapp.customer.exceptions;

import com.hotelapp.commons.dto.response.CustomResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
        public ResponseEntity<CustomResponse> handleDuplicateRecordException(SQLIntegrityConstraintViolationException ex) {
            CustomResponse customResponse = new CustomResponse();
            customResponse.setApi("/api/customers");
            customResponse.setCode(HttpStatus.BAD_REQUEST.toString());
            customResponse.setMessage("Error: duplicate record");
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
            Map<String, Object> errors = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

}


