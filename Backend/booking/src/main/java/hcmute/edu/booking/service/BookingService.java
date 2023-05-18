package hcmute.edu.booking.service;

import hcmute.edu.booking.DTO.BookingDTO;
import hcmute.edu.booking.model.Booking;
import hcmute.edu.booking.model.Review;

import java.util.List;
import java.util.Optional;

public interface BookingService {
  List<Booking> getAll();

  Optional<Booking> findById(int id);

  Object insert(Booking newBooking);

  Booking update(Booking newBooking, int id);

  boolean existsById(int id);

  void deleteById(int id);

  List<Booking> getAllBookingByHotelId(Integer hotelId);

  List<BookingDTO> getAllBookingByUserEmail(String userEmail);

  List<BookingDTO> getAllBookingByUserEmailAndStatus(String userEmail, String status);

  boolean checkBooking(Integer bookingId);
}
