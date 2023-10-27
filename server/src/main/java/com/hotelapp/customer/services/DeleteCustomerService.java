package com.hotelapp.customer.services;

import com.hotelapp.customer.facade.DeleteCustomerFacade;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomerService {

    private final DeleteCustomerFacade deleteCustomerFacade;

    public DeleteCustomerService(DeleteCustomerFacade deleteCustomerFacade){
        this.deleteCustomerFacade = deleteCustomerFacade;
    }

    public boolean deleteCustomer(Long id){
       return deleteCustomerFacade.delete(id);
    }
}
