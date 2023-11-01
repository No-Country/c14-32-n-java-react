package com.hotelapp.booking.facade;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.request.CustomerDataRequest;

import java.util.List;

public interface GetByCustomerDataFacade {

    List<Booking> getByCustomerData(CustomerDataRequest customerDataRequest);
}
