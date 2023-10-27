package com.hotelapp.booking.db.sql.jpaimpl;
import com.hotelapp.booking.db.sql.jparepository.BookingRepository;
import com.hotelapp.booking.db.sql.mapper.BookingMapper;
import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.response.BookingReport;
import com.hotelapp.booking.facade.CreateBookingFacade;
import com.hotelapp.booking.facade.GetAllBookingFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookingImpl implements CreateBookingFacade, GetAllBookingFacade {
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
    public Page<BookingReport> getAllBookings(int numberPage) {
        int pageSize = 10;
        PageRequest page = PageRequest.of(numberPage, pageSize);
        return bookingRepository.findAll(page).map(bookingMapper::bookingDataToBookingReport );
    }
}
