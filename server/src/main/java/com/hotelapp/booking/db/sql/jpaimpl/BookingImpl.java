package com.hotelapp.booking.db.sql.jpaimpl;
import com.hotelapp.booking.db.sql.jparepository.BookingRepository;
import com.hotelapp.booking.db.sql.mapper.BookingMapper;
import com.hotelapp.booking.db.sql.modeldata.BookingData;
import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.request.CustomerDataRequest;
import com.hotelapp.booking.facade.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingImpl implements CreateBookingFacade, GetAllBookingFacade, UpdateBookingFacade,
        GetBookingByIdFacade, DeleteBookingFacade,GetByCustomerDataFacade {
    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper;
    public BookingImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }


    @Override
    public Booking saveBooking(Booking booking) {
        System.out.println(booking);
        return bookingMapper.bookingDataToBooking(
                bookingRepository.save(bookingMapper.bookingToBookingData(booking))
        );
    }

    @Override
    public Page<Booking> getAllBookings(int numberPage) {
        int pageSize = 10;
        PageRequest page = PageRequest.of(numberPage, pageSize);
        return bookingRepository.findAll(page).map(bookingMapper::bookingDataToBooking);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        Optional<BookingData> bookingDataOptional = bookingRepository.findById(booking.getIdBooking());
        if (bookingDataOptional.isPresent()){
            BookingData updateBookingData = bookingRepository.
                    save(bookingMapper.bookingToBookingData(booking));
            return bookingMapper.bookingDataToBooking(updateBookingData);
        }
        return null;
    }


    @Override
    public Booking getBookingById(Long id) {
        Optional<BookingData> bookingDataOptional = bookingRepository.findById(id);
        if (bookingDataOptional.isPresent()){
            BookingData bookingData = bookingDataOptional.get();
            return bookingMapper.bookingDataToBooking(bookingData);
        }
        return null;
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> getByCustomerData(CustomerDataRequest customerDataRequest) {

        List<BookingData> bookingDataList = bookingRepository
                .findByCustomer_FirstnameContainingAndCustomer_LastnameContainingIgnoreCase
                        (customerDataRequest.firstname(),customerDataRequest.lastname());
        List<Booking> bookingList = new ArrayList<>();
        for (BookingData bookingData : bookingDataList) {
            Booking booking = bookingMapper.bookingDataToBooking(bookingData);
            bookingList.add(booking);
        }
        return bookingList;
    }
}
