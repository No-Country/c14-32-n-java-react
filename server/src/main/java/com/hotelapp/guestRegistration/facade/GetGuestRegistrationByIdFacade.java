package com.hotelapp.guestRegistration.facade;

import com.hotelapp.guestRegistration.dto.model.GuestRegistration;

public interface GetGuestRegistrationByIdFacade {

    GuestRegistration getById(Long id);
}
