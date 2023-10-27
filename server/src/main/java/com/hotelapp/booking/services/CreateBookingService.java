package com.hotelapp.booking.services;


import com.hotelapp.booking.dto.mappers.CreateBookingRequestMapper;
import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.model.enums.BookingState;
import com.hotelapp.booking.dto.request.CreateBookingRequest;
import com.hotelapp.booking.facade.CreateBookingFacade;
import com.hotelapp.booking.services.helper.CreateBookingHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateBookingService {
   private final CreateBookingFacade createBookingFacade;
   private final CreateBookingHelper createBookingHelper;
   private final CreateBookingRequestMapper createBookingRequestMapper;


    public CreateBookingService(CreateBookingFacade createBookingFacade, CreateBookingHelper createBookingHelper,
                                CreateBookingRequestMapper createBookingRequestMapper) {
        this.createBookingFacade = createBookingFacade;
        this.createBookingHelper = createBookingHelper;
        this.createBookingRequestMapper = createBookingRequestMapper;
    }
    public Booking saveBooking(CreateBookingRequest createBookingRequest) {
        Booking booking = createBookingRequestMapper.createBookingRequestToBooking(createBookingRequest);
        booking.setBookingState(BookingState.CONFIRMED);// BOOKING STATUS
        booking.setDate(LocalDateTime.now()); //DATE OF OPERATION BOOKING
        createBookingHelper.setBookingRoom(booking);
        createBookingHelper.setBookingCustomer(booking);
        return createBookingFacade.saveBooking(booking);
    }
}
