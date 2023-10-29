package com.hotelapp.booking.controller.validate;

import com.hotelapp.booking.dto.request.CreateBookingRequest;
import com.hotelapp.commons.controller.validators.Validator;
import com.hotelapp.commons.controller.validators.ValidatorObject;
import org.springframework.validation.BindingResult;
import static com.hotelapp.commons.constants.GlobalApiConstant.NOT_NULL;
import static java.util.Objects.isNull;


public class ValidateBooking extends Validator {
    private static final String OBJ = "CreateBookingRequest";

    public static void validateCreateBookingRequestRows(CreateBookingRequest createBookingRequest, BindingResult bindingResult){
        idRoomNotNull(createBookingRequest, bindingResult);
        idCustomerNotNull(createBookingRequest,bindingResult);
        checkInDateNotNull(createBookingRequest,bindingResult);
        checkOutDateNotNull(createBookingRequest,bindingResult);
        paymentTypeNotNull(createBookingRequest,bindingResult);
    }

    public static void idRoomNotNull(CreateBookingRequest createBookingRequest, BindingResult bindingResult){
        if(isNull(createBookingRequest.idRoom())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("idRoom")
                    .object(OBJ).build(), bindingResult);
        }
    }

    public static void idCustomerNotNull(CreateBookingRequest createBookingRequest, BindingResult bindingResult){
        if(isNull(createBookingRequest.idCustomer())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("idCustomer")
                    .object(OBJ).build(), bindingResult);
        }
    }

    public static void checkInDateNotNull(CreateBookingRequest createBookingRequest, BindingResult bindingResult){
        if(isNull(createBookingRequest.checkInDate())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("checkInDate")
                    .object(OBJ).build(), bindingResult);
        }
    }
    public static void checkOutDateNotNull(CreateBookingRequest createBookingRequest, BindingResult bindingResult){
        if(isNull(createBookingRequest.checkOutDate())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("checkOutDate")
                    .object(OBJ).build(), bindingResult);
        }
    }

    public static void paymentTypeNotNull(CreateBookingRequest createBookingRequest, BindingResult bindingResult){
        if(isNull(createBookingRequest.paymentType())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("paymentType")
                    .object(OBJ).build(), bindingResult);
        }
    }
}

