package com.hotelapp.booking.services;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.request.CustomerDataRequest;
import com.hotelapp.booking.dto.response.BookingReport;
import com.hotelapp.booking.facade.GetByCustomerDataFacade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetByCustomerDataService {

    private final GetByCustomerDataFacade getByCustomerDataFacade;

    public GetByCustomerDataService(GetByCustomerDataFacade getByCustomerDataFacade){
        this.getByCustomerDataFacade = getByCustomerDataFacade;
    }

    public List<BookingReport> getBookingByCustomerData(CustomerDataRequest customerDataRequest){
        List<Booking> bookingList = getByCustomerDataFacade.getByCustomerData(customerDataRequest);
        List<BookingReport> bookings = new ArrayList<>();
        for (Booking booking : bookingList) {
            BookingReport bookingReport = new BookingReport(booking);
            bookings.add(bookingReport);
        }
        return bookings;

    }
}
