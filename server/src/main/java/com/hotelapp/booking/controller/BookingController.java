package com.hotelapp.booking.controller;

import com.hotelapp.booking.dto.request.CreateBookingRequest;
import com.hotelapp.booking.dto.request.CustomerDataRequest;
import com.hotelapp.booking.dto.request.UpdateBookingRequest;
import com.hotelapp.commons.dto.response.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.hotelapp.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;
import static com.hotelapp.commons.constants.GlobalApiConstant.ID_PARAM;

public interface BookingController {

    @PostMapping
    ResponseEntity<CustomResponse> save(@RequestBody CreateBookingRequest booking, BindingResult bindingResult);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllBookings(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getBookingById(@PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> updateBooking(@RequestBody UpdateBookingRequest updateBookingRequest, BindingResult bindingResult );

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> deleteBookingById(@PathVariable Long id);

    @PostMapping("/search-booking")
    public ResponseEntity<CustomResponse> getBookingByGuestData(@Valid @RequestBody CustomerDataRequest customerDataRequest);

}
