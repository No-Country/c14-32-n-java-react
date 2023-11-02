package com.hotelapp.booking.services;


import com.hotelapp.booking.dto.mappers.CreateBookingRequestMapper;
import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.model.enums.BookingState;
import com.hotelapp.booking.dto.request.CreateBookingRequest;
import com.hotelapp.booking.dto.response.BookingReport;
import com.hotelapp.booking.facade.CreateBookingFacade;
import com.hotelapp.booking.services.helper.CreateBookingHelper;
import com.hotelapp.booking.services.validation.BookingsValidator;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CreateBookingService {
   private final CreateBookingFacade createBookingFacade;
   private final CreateBookingHelper createBookingHelper;
   private final CreateBookingRequestMapper createBookingRequestMapper;
   private final List<BookingsValidator> bookingsValidators;




    public CreateBookingService(CreateBookingFacade createBookingFacade, CreateBookingHelper createBookingHelper,
                                CreateBookingRequestMapper createBookingRequestMapper, List<BookingsValidator> bookingsValidators) {
        this.createBookingFacade = createBookingFacade;
        this.createBookingHelper = createBookingHelper;
        this.createBookingRequestMapper = createBookingRequestMapper;
        this.bookingsValidators = bookingsValidators;

    }
    public BookingReport saveBooking(CreateBookingRequest createBookingRequest) {
        Booking booking = createBookingRequestMapper.createBookingRequestToBooking(createBookingRequest);

        createBookingHelper.setBookingRoom(booking);
        createBookingHelper.setBookingCustomer(booking);
        createBookingHelper.setBooking(booking);
        bookingsValidators.forEach(v->v.validation(booking));//validate
        return new BookingReport(createBookingFacade.saveBooking(booking));
    }
}