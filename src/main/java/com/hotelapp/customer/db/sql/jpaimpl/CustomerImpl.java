package com.hotelapp.customer.db.sql.jpaimpl;

import com.hotelapp.customer.db.sql.jparepository.CustomerRepository;
import com.hotelapp.customer.db.sql.mapper.CustomerMapper;
import com.hotelapp.customer.db.sql.modeldata.CustomerData;
import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.customer.facade.CreateCustomerFacade;
import com.hotelapp.customer.facade.GetAllCustomersFacade;
import com.hotelapp.customer.facade.GetCustomerByIdFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerImpl implements CreateCustomerFacade, GetAllCustomersFacade, GetCustomerByIdFacade {
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
}
