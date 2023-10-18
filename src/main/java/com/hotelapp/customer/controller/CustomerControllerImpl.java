package com.hotelapp.customer.controller;

import com.hotelapp.commons.controller.GenericRestController;
import com.hotelapp.commons.dto.response.CustomResponse;
import com.hotelapp.customer.controller.validate.ValidateCustomer;
import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.customer.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hotelapp.commons.constants.GlobalApiConstant.*;
import static com.hotelapp.customer.constants.CustomerConstants.REQUEST_CUSTOMER;

@RestController
@RequestMapping(REQUEST_CUSTOMER)
public class CustomerControllerImpl extends GenericRestController implements CustomerController {


    private final CreateCustomerService createCustomerService;
    private final GetAllCustomersService getAllCustomersService;
    private final GetCustomerByIdService getCustomerByIdService;
    private final UpdateCustomerService updateCustomerService;
    private final DeleteCustomerService deleteCustomerService;

    public CustomerControllerImpl(CreateCustomerService createCustomerService,
                                  GetAllCustomersService getAllCustomersService,
                                  GetCustomerByIdService getCustomerByIdService,
                                  UpdateCustomerService updateCustomerService,
                                  DeleteCustomerService deleteCustomerService){
        this.createCustomerService = createCustomerService;
        this.getAllCustomersService = getAllCustomersService;
        this.getCustomerByIdService = getCustomerByIdService;
        this.updateCustomerService = updateCustomerService;
        this.deleteCustomerService = deleteCustomerService;
    }


    @Override
    public ResponseEntity<CustomResponse> save(Customer customer, BindingResult bindingResult) {
        ValidateCustomer.validateCustomerRows(customer, bindingResult);
        if (bindingResult.hasErrors()){
            return bad(customer,bindingResult.getFieldError().getDefaultMessage(),REQUEST_CUSTOMER);
        }
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
        return notFound(null,NOT_FOUND, REQUEST_CUSTOMER );
    }

    @Override
    public ResponseEntity<CustomResponse> update(Customer customer, BindingResult bindingResult) {
        ValidateCustomer.validateCustomerRows(customer, bindingResult);
        if (bindingResult.hasErrors()){
            return bad(customer,bindingResult.getFieldError().getDefaultMessage(),REQUEST_CUSTOMER);
        }
        Customer updateCustomerDate = updateCustomerService.updateCustomer(customer);
        if (updateCustomerDate != null){
            return ok(updateCustomerDate,null,REQUEST_CUSTOMER);
        }
        return notFound(null,NOT_FOUND, REQUEST_CUSTOMER );
    }

    @Override
    public ResponseEntity<CustomResponse> delete(Long id) {
        boolean result = deleteCustomerService.deleteCustomer(id);
        if (result) return ok(null,DELETED_SUCCESSFULLY, REQUEST_CUSTOMER);

        return notFound(null,NOT_FOUND,REQUEST_CUSTOMER);
    }

}
