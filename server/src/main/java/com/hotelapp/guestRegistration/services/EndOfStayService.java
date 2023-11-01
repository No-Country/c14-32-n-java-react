package com.hotelapp.guestRegistration.services;

import com.hotelapp.guestRegistration.dto.model.GuestRegistration;
import com.hotelapp.guestRegistration.facade.CreateRegistrationFacade;
import com.hotelapp.guestRegistration.facade.EndOfStayFacade;
import com.hotelapp.guestRegistration.services.helper.CreateRegistrationHelper;
import org.springframework.stereotype.Service;


@Service
public class EndOfStayService {

    private final CreateRegistrationHelper createRegistrationHelper;
    private final EndOfStayFacade endOfStayFacade;

    public EndOfStayService(CreateRegistrationHelper createRegistrationHelper, EndOfStayFacade endOfStayFacade){
        this.createRegistrationHelper = createRegistrationHelper;
        this.endOfStayFacade = endOfStayFacade;
    }

    public GuestRegistration endOfStay(Long id){
        GuestRegistration guestRegistration = createRegistrationHelper.setGuestRegistration(id);
        createRegistrationHelper.setRegistrationRoom(guestRegistration.getRoom());
        return endOfStayFacade.updateGuestRegistration(guestRegistration);
        }
}
