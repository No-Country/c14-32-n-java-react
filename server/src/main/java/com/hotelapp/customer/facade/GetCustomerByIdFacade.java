package com.hotelapp.customer.facade;

import com.hotelapp.customer.dto.model.Customer;

public interface GetCustomerByIdFacade {
    Customer getCustomerById(Long id);
}
