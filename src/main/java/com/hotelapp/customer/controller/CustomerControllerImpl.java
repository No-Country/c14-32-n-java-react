package com.hotelapp.customer.controller;

import com.hotelapp.commons.controller.GenericRestController;
import com.hotelapp.commons.dto.response.CustomResponse;
import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.customer.services.CreateCustomerService;
import com.hotelapp.customer.services.GetAllCustomersService;
import com.hotelapp.customer.services.GetCustomerByIdService;
import com.hotelapp.customer.services.UpdateCustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hotelapp.commons.constants.GlobalApiConstant.CREATED;
import static com.hotelapp.commons.constants.GlobalApiConstant.NOT_FOUND;
import static com.hotelapp.customer.constants.CustomerConstants.REQUEST_CUSTOMER;

@RestController
@RequestMapping(REQUEST_CUSTOMER)
public class CustomerControllerImpl extends GenericRestController implements CustomerController {


    private final CreateCustomerService createCustomerService;
    private final GetAllCustomersService getAllCustomersService;
    private final GetCustomerByIdService getCustomerByIdService;
    private final UpdateCustomerService updateCustomerService;

    public CustomerControllerImpl(CreateCustomerService createCustomerService,
                                  GetAllCustomersService getAllCustomersService,
                                  GetCustomerByIdService getCustomerByIdService,
                                  UpdateCustomerService updateCustomerService){
        this.createCustomerService = createCustomerService;
        this.getAllCustomersService = getAllCustomersService;
        this.getCustomerByIdService = getCustomerByIdService;
        this.updateCustomerService = updateCustomerService;
    }


    @Override
    public ResponseEntity<CustomResponse> save(Customer customer) {
        return create(createCustomerService.saveCustomer(customer),CREATED, REQUEST_CUSTOMER);
    }

    @Override
    public ResponseEntity<CustomResponse> getAll(int numberPage) {
        return ok(getAllCustomersService.getAllCustomersPaginator(numberPage),null,REQUEST_CUSTOMER);
    }

    @Override
    public ResponseEntity<CustomResponse> getById(Long id) {
        Customer customer = getCustomerByIdService.getCustomerById(id);
        if (customer != null){
            return ok(customer,null,REQUEST_CUSTOMER);
        }
        return ok(null,NOT_FOUND, REQUEST_CUSTOMER );
    }

    @Override
    public ResponseEntity<CustomResponse> update(Customer customer) {
        Customer updateCustomerDate = updateCustomerService.updateCustomer(customer);
        if (updateCustomerDate != null){
            return ok(updateCustomerDate,null,REQUEST_CUSTOMER);
        }
        return ok(null,NOT_FOUND, REQUEST_CUSTOMER );
    }

}
