package com.hotelapp.guestRegistration.facade;

import com.hotelapp.guestRegistration.dto.model.GuestRegistration;
import org.springframework.data.domain.Page;

public interface GetAllRegistrationFacade {
    Page<GuestRegistration> getAllPaginator(int numberPage);
}
