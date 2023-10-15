package com.hotelapp.customer.services;

import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.customer.facade.UpdateCustomerFacada;
import org.springframework.stereotype.Service;

@Service
public class UpdateCustomerService {

    private final UpdateCustomerFacada updateCustomerFacada;

    public UpdateCustomerService(UpdateCustomerFacada updateCustomerFacada){
        this.updateCustomerFacada = updateCustomerFacada;
    }

    public Customer updateCustomer(Customer customer){
       return updateCustomerFacada.updateCustomer(customer);
    }
}
