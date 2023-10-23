package com.hotelapp.commons.controller;
import com.hotelapp.commons.dto.response.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenericRestController {
    public GenericRestController() {
        // TODO document why this constructor is empty
    }

    protected ResponseEntity<CustomResponse> create(Object obj, String message, String api ){
        CustomResponse customResponse = new CustomResponse();
        customResponse.setApi(api);
        customResponse.setCode(HttpStatus.CREATED.toString());
        customResponse.setMessage(message);
        customResponse.setResponse(obj);
        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
    }
    protected ResponseEntity<CustomResponse> ok(Object obj, String message, String api ){
        CustomResponse customResponse = new CustomResponse();
        customResponse.setApi(api);
        customResponse.setCode(HttpStatus.OK.toString());
        customResponse.setMessage(message);
        customResponse.setResponse(obj);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    protected ResponseEntity<CustomResponse> bad(Object obj,String message, String api ){
        CustomResponse customResponse = new CustomResponse();
        customResponse.setApi(api);
        customResponse.setCode(HttpStatus.BAD_REQUEST.toString());
        customResponse.setMessage(message);
        customResponse.setResponse(obj);
        return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<CustomResponse> notFound(Object obj, String message, String api) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setApi(api);
        customResponse.setCode(HttpStatus.NOT_FOUND.toString());
        customResponse.setMessage(message);
        customResponse.setResponse(obj);
        return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
    }




}
