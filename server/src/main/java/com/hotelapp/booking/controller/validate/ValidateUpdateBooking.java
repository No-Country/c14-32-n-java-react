package com.hotelapp.booking.controller.validate;

import com.hotelapp.booking.dto.request.CreateBookingRequest;
import com.hotelapp.booking.dto.request.UpdateBookingRequest;
import com.hotelapp.commons.controller.validators.Validator;
import com.hotelapp.commons.controller.validators.ValidatorObject;
import org.springframework.validation.BindingResult;

import static com.hotelapp.commons.constants.GlobalApiConstant.NOT_NULL;
import static java.util.Objects.isNull;

public class ValidateUpdateBooking extends Validator {

    private static final String OBJ = "UpdateBookingRequest";

    public static void validateCreateBookingRequestRows(UpdateBookingRequest booking, BindingResult bindingResult){
        idBookingNotNull(booking, bindingResult);
        idRoomNotNull(booking, bindingResult);
        idCustomerNotNull(booking,bindingResult);
        checkInDateNotNull(booking,bindingResult);
        checkOutDateNotNull(booking,bindingResult);
        paymentTypeNotNull(booking,bindingResult);
        bookingStateNotNull(booking,bindingResult);
    }

    public static void idBookingNotNull(UpdateBookingRequest booking, BindingResult bindingResult){
        if(isNull(booking.idBooking())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("idBooking")
                    .object(OBJ).build(), bindingResult);
        }
    }
    public static void idRoomNotNull(UpdateBookingRequest booking, BindingResult bindingResult){
        if(isNull(booking.idRoom())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("idRoom")
                    .object(OBJ).build(), bindingResult);
        }
    }

    public static void idCustomerNotNull(UpdateBookingRequest booking, BindingResult bindingResult){
        if(isNull(booking.idCustomer())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("idCustomer")
                    .object(OBJ).build(), bindingResult);
        }
    }

    public static void checkInDateNotNull(UpdateBookingRequest booking, BindingResult bindingResult){
        if(isNull(booking.checkInDate())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("checkInDate")
                    .object(OBJ).build(), bindingResult);
        }
    }
    public static void checkOutDateNotNull(UpdateBookingRequest booking, BindingResult bindingResult){
        if(isNull(booking.checkOutDate())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("checkOutDate")
                    .object(OBJ).build(), bindingResult);
        }
    }

    public static void paymentTypeNotNull(UpdateBookingRequest booking, BindingResult bindingResult){
        if(isNull(booking.paymentType())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("paymentType")
                    .object(OBJ).build(), bindingResult);
        }
    }

    public static void bookingStateNotNull(UpdateBookingRequest booking, BindingResult bindingResult){
        if(isNull(booking.bookingState())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("bookingState")
                    .object(OBJ).build(), bindingResult);
        }
    }

}
