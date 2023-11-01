package com.hotelapp.infra.error;


public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String e) {
        super(e);
    }

}