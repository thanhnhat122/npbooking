package hcmute.edu.booking.repository;

import hcmute.edu.booking.model.DetailBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DetailBookingRepository extends JpaRepository<DetailBooking, Integer> {
  List<DetailBooking> findAllByBookingId(Integer bookingId);

  List<DetailBooking> findAllByRoomId(Integer roomId);
}
