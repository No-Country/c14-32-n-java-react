package com.hotelapp.booking.services;

import com.hotelapp.booking.dto.mappers.CreateBookingRequestMapper;
import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.request.UpdateBookingRequest;
import com.hotelapp.booking.dto.response.BookingReport;
import com.hotelapp.booking.facade.UpdateBookingFacade;
import com.hotelapp.booking.services.helper.CreateBookingHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UpdateBookingService {

    private final UpdateBookingFacade updateBookingFacade;
    private final CreateBookingRequestMapper createBookingRequestMapper;
    private final CreateBookingHelper createBookingHelper;

    public UpdateBookingService(UpdateBookingFacade updateBookingFacade,
                                CreateBookingRequestMapper createBookingRequestMapper,
                                CreateBookingHelper createBookingHelper){
        this.updateBookingFacade = updateBookingFacade;
        this.createBookingRequestMapper = createBookingRequestMapper;
        this.createBookingHelper = createBookingHelper;
    }

    public BookingReport updateBooking(UpdateBookingRequest updateBookingRequest){
        Booking booking = createBookingRequestMapper.updateBookingRequestToBooking(updateBookingRequest);

        booking.setDate(new Date()); //DATE OF OPERATION BOOKING
        createBookingHelper.setBookingRoom(booking);
        createBookingHelper.setBookingCustomer(booking);
        return new BookingReport(updateBookingFacade.updateBooking(booking));


    }

}
