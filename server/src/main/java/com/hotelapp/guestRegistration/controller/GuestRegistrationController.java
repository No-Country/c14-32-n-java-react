package com.hotelapp.guestRegistration.controller;

import com.hotelapp.commons.dto.response.CustomResponse;
import com.hotelapp.guestRegistration.dto.request.RegistrationCheckOutRequest;
import com.hotelapp.guestRegistration.dto.request.RegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.hotelapp.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;
import static com.hotelapp.commons.constants.GlobalApiConstant.ID_PARAM;

public interface GuestRegistrationController {

    @PostMapping
    ResponseEntity<CustomResponse> saveCheckInRegistration(@RequestBody RegistrationRequest registrationRequest);

    @PostMapping("/check-out")
    ResponseEntity<CustomResponse> saveCheckOutRegistration(@RequestBody RegistrationCheckOutRequest registrationCheckOutRequest);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllRegistration(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getRegistrationById(@PathVariable Long id);
}
