package com.hotelapp.booking.controller;

import com.hotelapp.booking.controller.validate.ValidateBooking;
import com.hotelapp.booking.controller.validate.ValidateUpdateBooking;
import com.hotelapp.booking.dto.request.CreateBookingRequest;
import com.hotelapp.booking.dto.request.CustomerDataRequest;
import com.hotelapp.booking.dto.request.UpdateBookingRequest;
import com.hotelapp.booking.dto.response.BookingReport;
import com.hotelapp.booking.services.*;
import com.hotelapp.commons.controller.GenericRestController;
import com.hotelapp.commons.dto.response.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.hotelapp.booking.constants.BookingConstants.REQUEST_BOOKING;
import static com.hotelapp.commons.constants.GlobalApiConstant.*;

@RestController
@RequestMapping(REQUEST_BOOKING)
public class BookingControllerImpl extends GenericRestController implements BookingController  {
    private final CreateBookingService createBookingService;
    private final GetAllBookingService getAllBookingService;
    private final GetBookingByIdService getBookingByIdService;
    private final UpdateBookingService updateBookingService;
    private final DeleteBookingService deleteBookingService;
    private final GetByCustomerDataService getByCustomerDataService;
    public BookingControllerImpl(CreateBookingService createBookingService, GetAllBookingService getAllBookingService,
                                 GetBookingByIdService getBookingByIdService,UpdateBookingService updateBookingService,
                                 DeleteBookingService deleteBookingService,GetByCustomerDataService getByCustomerDataService) {
        this.createBookingService   = createBookingService;
        this.getAllBookingService   = getAllBookingService;
        this.getBookingByIdService  = getBookingByIdService;
        this.updateBookingService   = updateBookingService;
        this.deleteBookingService   = deleteBookingService;
        this.getByCustomerDataService  = getByCustomerDataService;
    }


    @Override
    public ResponseEntity<CustomResponse> save(CreateBookingRequest createBookingRequest, BindingResult bindingResult) {
        ValidateBooking.validateCreateBookingRequestRows(createBookingRequest,bindingResult);
        if(bindingResult.hasErrors()){
            return bad(createBookingRequest,bindingResult.getFieldError().getDefaultMessage(),REQUEST_BOOKING);
        }
        return ok(createBookingService.saveBooking(createBookingRequest),CREATED,REQUEST_BOOKING);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllBookings(int numberPage) {
        return ok(getAllBookingService.getAllBookingsPage(numberPage), null, REQUEST_BOOKING);
    }

    @Override
    public ResponseEntity<CustomResponse> getBookingById(Long id) {
        BookingReport booking = getBookingByIdService.getBookingById(id);
        if (booking != null){
            return  ok(booking,null,REQUEST_BOOKING);
        }
        return notFound(null,NOT_FOUND, REQUEST_BOOKING );
    }

    @Override
    public ResponseEntity<CustomResponse> updateBooking(UpdateBookingRequest updateBookingRequest, BindingResult bindingResult){
        ValidateUpdateBooking.validateCreateBookingRequestRows(updateBookingRequest,bindingResult);
        if(bindingResult.hasErrors()){
            return bad(updateBookingRequest,bindingResult.getFieldError().getDefaultMessage(),REQUEST_BOOKING);
        }
        BookingReport booking = updateBookingService.updateBooking(updateBookingRequest);
        if (booking != null){
            return ok(booking,null,REQUEST_BOOKING);
        }
        return notFound(null,NOT_FOUND, REQUEST_BOOKING );
    }

    @Override
    public ResponseEntity<CustomResponse> deleteBookingById(Long id) {
        deleteBookingService.deleteBooking(id);
        return ok(null,DELETED_SUCCESSFULLY, REQUEST_BOOKING);
    }

    @Override
    public ResponseEntity<CustomResponse> getBookingByGuestData(CustomerDataRequest customerDataRequest){
        List<BookingReport> bookingReportList = getByCustomerDataService.getBookingByCustomerData(customerDataRequest);
        if (bookingReportList.isEmpty()) return notFound(null,NOT_FOUND, REQUEST_BOOKING );
        return ok(bookingReportList,null,REQUEST_BOOKING);
    }
}
