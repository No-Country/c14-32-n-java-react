package com.hotelapp.customer.services;

import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.customer.facade.GetCustomerByIdFacade;
import org.springframework.stereotype.Service;

@Service
public class GetCustomerByIdService {
    private final GetCustomerByIdFacade getCustomerByIdFacade;

    public GetCustomerByIdService(GetCustomerByIdFacade getCustomerByIdFacade){
        this.getCustomerByIdFacade = getCustomerByIdFacade;
    }

    public Customer getCustomerById(Long id){
        return getCustomerByIdFacade.getCustomerById(id);
    }

}
