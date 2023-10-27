package com.hotelapp.customer.facade;

import com.hotelapp.customer.dto.model.Customer;

public interface CreateCustomerFacade {
    Customer saveCustomer(Customer customer);
}
