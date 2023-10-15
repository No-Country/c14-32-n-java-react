package com.hotelapp.customer.controller;

import com.hotelapp.commons.dto.response.CustomResponse;
import com.hotelapp.customer.dto.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hotelapp.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;

public interface CustomerController {

    @PostMapping
    ResponseEntity<CustomResponse> save(@RequestBody Customer customer);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAll(@PathVariable int numberPage);


}
