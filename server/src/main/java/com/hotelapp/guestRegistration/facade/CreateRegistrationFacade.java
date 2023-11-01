package com.hotelapp.guestRegistration.facade;

import com.hotelapp.guestRegistration.dto.model.GuestRegistration;

public interface CreateRegistrationFacade {
    GuestRegistration save(GuestRegistration guestRegistration);
}
