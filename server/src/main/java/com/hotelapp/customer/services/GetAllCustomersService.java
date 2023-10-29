package com.hotelapp.customer.services;

import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.customer.facade.GetAllCustomersFacade;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class GetAllCustomersService {

    private final GetAllCustomersFacade getAllCustomersFacade;

    public GetAllCustomersService(GetAllCustomersFacade getAllCustomersFacade){
        this.getAllCustomersFacade = getAllCustomersFacade;
    }

    public Page<Customer> getAllCustomersPaginator(int numberPage){
        return getAllCustomersFacade.getAllCustomersPaginator(numberPage);
    }
}
