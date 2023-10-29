package com.hotelapp.customer.db.sql.jpaimpl;

import com.hotelapp.customer.db.sql.jparepository.CustomerRepository;
import com.hotelapp.customer.db.sql.mapper.CustomerMapper;
import com.hotelapp.customer.db.sql.modeldata.CustomerData;
import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.customer.facade.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class CustomerImpl implements CreateCustomerFacade, GetAllCustomersFacade,
        GetCustomerByIdFacade, UpdateCustomerFacada, DeleteCustomerFacade {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public CustomerImpl(CustomerRepository customerRepository, CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerMapper.customerDataToCustomer(customerRepository
                .save(customerMapper.customerToCustomerData(customer))) ;
    }

    @Override
    public Page<Customer> getAllCustomersPaginator(int numberPage) {
        int pageSize = 10;
        PageRequest page = PageRequest.of(numberPage,pageSize);
        Page<CustomerData> customerData = customerRepository.findAll(page);
        return customerData.map(customerMapper::customerDataToCustomer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<CustomerData> customerDataOptional = customerRepository.findById(id);
        if (customerDataOptional.isPresent()) {
            CustomerData customerData = customerDataOptional.get();
            return customerMapper.customerDataToCustomer(customerData);
        }
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Optional<CustomerData> customerDataOptional = customerRepository
                .findById(customer.getIdCustomer());
        if (customerDataOptional.isPresent()){
            CustomerData updatedCustomerData = customerRepository
                    .save(customerMapper.customerToCustomerData(customer));
            return customerMapper.customerDataToCustomer(updatedCustomerData);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Optional<CustomerData> customerDataOptional = customerRepository.findById(id);
        if (customerDataOptional.isPresent()) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
