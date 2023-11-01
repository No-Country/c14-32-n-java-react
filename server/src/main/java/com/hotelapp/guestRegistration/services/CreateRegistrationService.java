package com.hotelapp.guestRegistration.services;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.facade.GetBookingByIdFacade;
import com.hotelapp.guestRegistration.dto.mappers.CreateGuestRegistrationRequestMapper;
import com.hotelapp.guestRegistration.dto.model.GuestRegistration;
import com.hotelapp.guestRegistration.facade.CreateRegistrationFacade;
import com.hotelapp.guestRegistration.services.helper.CreateRegistrationHelper;
import org.springframework.stereotype.Service;

@Service
public class CreateRegistrationService {

    private final CreateRegistrationFacade createRegistrationFacade;
    private final CreateRegistrationHelper createRegistrationHelper;
    private final GetBookingByIdFacade getBookingByIdFacade;
    private final CreateGuestRegistrationRequestMapper createGuestRegistrationRequestMapper;

    public CreateRegistrationService(CreateRegistrationFacade createRegistrationFacade,
                                     CreateRegistrationHelper createRegistrationHelper,
                                     GetBookingByIdFacade getBookingByIdFacade,
                                     CreateGuestRegistrationRequestMapper createGuestRegistrationRequestMapper){

        this.createRegistrationFacade = createRegistrationFacade;
        this.createRegistrationHelper = createRegistrationHelper;
        this.getBookingByIdFacade = getBookingByIdFacade;
        this.createGuestRegistrationRequestMapper = createGuestRegistrationRequestMapper;

    }

    public GuestRegistration saveRegistration(Long idBooking){
        Booking booking = getBookingByIdFacade.getBookingById(idBooking); //GET BOOKING

        createRegistrationHelper.setRegistrationBooking(booking); //BOOKING SET
        createRegistrationHelper.setRegistrationRoom(booking.getRoom()); //ROOM SET
        return createRegistrationFacade.save(createGuestRegistrationRequestMapper
                .createBookingToGuestRegistration(booking));
    }
}
