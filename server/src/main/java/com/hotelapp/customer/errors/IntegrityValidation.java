package com.hotelapp.customer.errors;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String e) {
        super(e);
    }
}
