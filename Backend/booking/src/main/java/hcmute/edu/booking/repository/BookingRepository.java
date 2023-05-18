package hcmute.edu.booking.repository;

import hcmute.edu.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findAllByHotelId(Integer hotelId);

    List<Booking> findAllByUserEmail(String email);

    Booking findBookingById(int bookingId);

    List<Booking> findAllByUserEmailAndStatus(String userEmail, String status);

    List<Booking> findAllByStatus(String status);


    List<Booking> findAllByStatusAndHotelId(String status, Integer hotelId);
}
