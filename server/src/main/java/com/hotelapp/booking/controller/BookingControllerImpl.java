package com.hotelapp.booking.controller;

import com.hotelapp.booking.dto.request.CreateBookingRequest;
import com.hotelapp.booking.services.CreateBookingService;
import com.hotelapp.booking.services.GetAllBookingService;
import com.hotelapp.commons.controller.GenericRestController;
import com.hotelapp.commons.dto.response.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hotelapp.booking.constants.BookingConstants.REQUEST_BOOKING;
import static com.hotelapp.commons.constants.GlobalApiConstant.CREATED;

@RestController
@RequestMapping(REQUEST_BOOKING)
public class BookingControllerImpl extends GenericRestController implements BookingController  {
    private final CreateBookingService createBookingService;
    private final GetAllBookingService getAllBookingService;
    public BookingControllerImpl(CreateBookingService createBookingService, GetAllBookingService getAllBookingService) {
        this.createBookingService = createBookingService;
        this.getAllBookingService = getAllBookingService;
    }


    @Override
    public ResponseEntity<CustomResponse> save(CreateBookingRequest booking, BindingResult bindingResult) {
        return ok(createBookingService.saveBooking(booking),CREATED,REQUEST_BOOKING);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllBookings(int numberPage) {
        return ok(getAllBookingService.getAllBookingsPage(numberPage), null, REQUEST_BOOKING);
    }

    @Override
    public ResponseEntity<CustomResponse> getBookingById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<CustomResponse> deleteBookingById(Long id) {
        return null;
    }
}
