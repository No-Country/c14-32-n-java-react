package com.hotelapp.booking.db.sql.jpaimpl;
import com.hotelapp.booking.db.sql.jparepository.BookingRepository;
import com.hotelapp.booking.db.sql.mapper.BookingMapper;
import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.facade.CreateBookingFacade;
import org.springframework.stereotype.Repository;

@Repository
public class BookingImpl implements CreateBookingFacade {
    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper;
    public BookingImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }


    @Override
    public Booking saveBooking(Booking booking) {
        return bookingMapper.bookingDataToBooking(
                bookingRepository.save(bookingMapper.bookingToBookingData(booking))
        );
    }

}
