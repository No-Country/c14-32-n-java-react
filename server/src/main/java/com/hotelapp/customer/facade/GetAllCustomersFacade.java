package com.hotelapp.customer.facade;

import com.hotelapp.customer.dto.model.Customer;
import org.springframework.data.domain.Page;

public interface GetAllCustomersFacade {
    Page<Customer> getAllCustomersPaginator(int numberPage);
}
