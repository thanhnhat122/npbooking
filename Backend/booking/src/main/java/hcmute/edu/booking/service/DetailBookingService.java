package hcmute.edu.booking.service;

import hcmute.edu.booking.DTO.DetailBookingDTO;
import hcmute.edu.booking.model.DetailBooking;

import java.util.List;
import java.util.Optional;

public interface DetailBookingService {
  List<DetailBooking> getAll();

  Optional<DetailBooking> findById(Integer id);

  DetailBooking insert(DetailBooking newDetailBooking);

  DetailBooking update(DetailBooking newDetailBooking, Integer id);

  boolean existsById(Integer id);

  void deleteById(Integer id);

  List<DetailBookingDTO> getAllDetailBookingByBookingId(Integer bookingId);
}
