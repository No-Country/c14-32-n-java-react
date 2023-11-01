package com.hotelapp.infra.error;


import com.hotelapp.commons.dto.response.CustomResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;


@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity treatError404(){
        return ResponseEntity.notFound().build();
    }


    /*@ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity errorHandlerIntegrity(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }*/

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerBusinessValidation(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<CustomResponse> handleNullPointerException(NullPointerException e) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setCode(HttpStatus.BAD_REQUEST.toString());
        customResponse.setMessage("Error: An unexpected error occurred:");
        return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
    }



    //INVALID ARGUMENTS
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity treatError400(MethodArgumentNotValidException e){
        var error = e.getFieldErrors().stream().map(ValidationErrorData::new).toList();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<CustomResponse> handleDuplicateRecordException() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setCode(HttpStatus.BAD_REQUEST.toString());
        customResponse.setMessage("Error: duplicate record");
        return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
    }

    private record ValidationErrorData(String field, String error){
        public ValidationErrorData(FieldError error){
            this(error.getField(),error.getDefaultMessage());
        }
    }


    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity<CustomResponse> handleIntegrityValidation(IntegrityValidation ex) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setCode(HttpStatus.BAD_REQUEST.toString());
        customResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
    }

}