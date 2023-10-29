package com.hotelapp.customer.services;

import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.customer.facade.CreateCustomerFacade;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerService{
    private final CreateCustomerFacade createCustomerFacade;

    public CreateCustomerService(CreateCustomerFacade createCustomerFacade){
        this.createCustomerFacade = createCustomerFacade;
    }

    public Customer saveCustomer(Customer customer){
        return createCustomerFacade.saveCustomer(customer);
    }

}
