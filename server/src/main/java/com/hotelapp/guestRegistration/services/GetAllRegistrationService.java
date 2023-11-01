package com.hotelapp.guestRegistration.services;

import com.hotelapp.guestRegistration.dto.model.GuestRegistration;
import com.hotelapp.guestRegistration.facade.GetAllRegistrationFacade;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class GetAllRegistrationService {

    private final GetAllRegistrationFacade getAllRegistrationFacade;

    public GetAllRegistrationService(GetAllRegistrationFacade getAllRegistrationFacade){
        this.getAllRegistrationFacade = getAllRegistrationFacade;
    }

    public Page<GuestRegistration> getAll(int numberPage){
        return getAllRegistrationFacade.getAllPaginator(numberPage);
    }
}
