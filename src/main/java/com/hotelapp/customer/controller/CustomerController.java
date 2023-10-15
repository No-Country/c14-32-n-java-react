package com.hotelapp.customer.controller;

import com.hotelapp.commons.dto.response.CustomResponse;
import com.hotelapp.customer.dto.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hotelapp.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;
import static com.hotelapp.commons.constants.GlobalApiConstant.ID_PARAM;

public interface CustomerController {

    @PostMapping
    ResponseEntity<CustomResponse> save(@RequestBody Customer customer);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAll(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getById(@PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> update(@RequestBody Customer customer);

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> delete(@PathVariable Long id);

}
