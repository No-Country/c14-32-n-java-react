package com.hotelapp.customer.controller;

import com.hotelapp.commons.dto.response.CustomResponse;
import com.hotelapp.customer.dto.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CustomerController {

    @PostMapping
    ResponseEntity<CustomResponse> save(@RequestBody Customer customer);

}
