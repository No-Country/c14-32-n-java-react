package com.hotelapp.customer.controller.validate;

import com.hotelapp.commons.controller.validators.Validator;
import com.hotelapp.commons.controller.validators.ValidatorObject;
import com.hotelapp.customer.dto.model.Customer;
import org.springframework.validation.BindingResult;

import static com.hotelapp.commons.constants.GlobalApiConstant.NOT_NULL;
import static java.util.Objects.isNull;


public class ValidateCustomer extends Validator {
    private static final String OBJ = "Customer";

    public static void validateCustomerRows(Customer customer, BindingResult bindingResult){
        firstnameNotNull(customer, bindingResult);
        lastnameNotNull(customer,bindingResult);
        dniNotNull(customer,bindingResult);
        emailNotNull(customer,bindingResult);
        phoneNotNull(customer,bindingResult);
    }


    public static void firstnameNotNull(Customer customer, BindingResult bindingResult){
        if(isNull(customer.getFirstname())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("firstname")
                    .object(OBJ).build(), bindingResult);
        }
    }
    public static void lastnameNotNull(Customer customer, BindingResult bindingResult){
        if(isNull(customer.getLastname())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("lastname")
                    .object(OBJ).build(), bindingResult);
        }
    }

    public static void dniNotNull(Customer customer, BindingResult bindingResult){
        if(isNull(customer.getDni())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("dni")
                    .object(OBJ).build(), bindingResult);
        }
    }

    public static void emailNotNull(Customer customer, BindingResult bindingResult){
        if(isNull(customer.getEmail())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("email")
                    .object(OBJ).build(), bindingResult);
        }
    }

    public static void phoneNotNull(Customer customer, BindingResult bindingResult){
        if(isNull(customer.getPhone())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("phone")
                    .object(OBJ).build(), bindingResult);
        }
    }

}

