package com.hotelapp.customer.db.sql.mapper;


import com.hotelapp.customer.db.sql.modeldata.CustomerData;
import com.hotelapp.customer.dto.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer customerDataToCustomer(CustomerData customerData){
        return new Customer.CustomerBuilder()
                .idCustomer(customerData.getIdCustomer())
                .firstname(customerData.getFirstname())
                .lastname(customerData.getLastname())
                .dni(customerData.getDni())
                .email(customerData.getEmail())
                .phone(customerData.getPhone())
                .build();
    }
    public CustomerData customerToCustomerData(Customer customer){
        return new CustomerData.CustomerDataBuilder()
                .idCustomer(customer.getIdCustomer())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .dni(customer.getDni())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }
}
