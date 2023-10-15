package com.hotelapp.customer.controller;

import com.hotelapp.commons.controller.GenericRestController;
import com.hotelapp.commons.dto.response.CustomResponse;
import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.customer.services.CreateCustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hotelapp.commons.constants.GlobalApiConstant.CREATED;
import static com.hotelapp.customer.constants.CustomerConstants.REQUEST_CUSTOMER;

@RestController
@RequestMapping(REQUEST_CUSTOMER)
public class CustomerControllerImpl extends GenericRestController implements CustomerController {


    private final CreateCustomerService createCustomerService;

    public CustomerControllerImpl(CreateCustomerService createCustomerService){
        this.createCustomerService = createCustomerService;

    }


    @Override
    public ResponseEntity<CustomResponse> save(Customer customer) {
        return create(createCustomerService.saveCustomer(customer),CREATED, REQUEST_CUSTOMER);
    }


}
