package com.hotelapp.customer.db.sql.jpaimpl;

import com.hotelapp.customer.db.sql.jparepository.CustomerRepository;
import com.hotelapp.customer.db.sql.mapper.CustomerMapper;
import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.customer.facade.CreateCustomerFacade;
import org.springframework.stereotype.Service;


@Service
public class CustomerImpl implements CreateCustomerFacade{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public CustomerImpl(CustomerRepository customerRepository, CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerMapper.customerDataToCustomer(customerRepository
                .save(customerMapper.customerToRoomData(customer))) ;
    }


}
