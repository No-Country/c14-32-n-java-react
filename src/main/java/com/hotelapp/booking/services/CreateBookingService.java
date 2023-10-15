package com.hotelapp.booking.services;


import com.hotelapp.booking.dto.mappers.CreateBookingRequestMapper;
import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.request.CreateBookingRequest;
import com.hotelapp.booking.facade.CreateBookingFacade;
import com.hotelapp.booking.services.helper.CreateBookingHelper;
import org.springframework.stereotype.Service;

@Service
public class CreateBookingService {
   private final CreateBookingFacade createBookingFacade;
   private final CreateBookingHelper createBookingHelper;
   private final CreateBookingRequestMapper createBookingRequestMapper;
    public CreateBookingService(CreateBookingFacade createBookingFacade, CreateBookingHelper createBookingHelper, CreateBookingRequestMapper createBookingRequestMapper) {
        this.createBookingFacade = createBookingFacade;
        this.createBookingHelper = createBookingHelper;
        this.createBookingRequestMapper = createBookingRequestMapper;
    }
    public Booking saveBooking(CreateBookingRequest createBookingRequest) {
        Booking booking= createBookingRequestMapper.createBookingRequestToBooking(createBookingRequest);
        createBookingHelper.setBookingRoom(booking);
        return createBookingFacade.saveBooking(booking);
    }
}
