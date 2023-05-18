package hcmute.edu.booking.service;

import hcmute.edu.booking.DTO.HotelDTO;
import hcmute.edu.booking.filter.Filter;
import hcmute.edu.booking.model.Hotel;
import hcmute.edu.booking.model.Statistic;

import java.util.List;
import java.util.Optional;

public interface HotelService {
  List<Hotel> getAll();

  Optional<Hotel> findById(int id);

  Object insert(Hotel newHotel);

  Hotel update(Hotel newHotel, int id);

  boolean existsById(int id);

  void deleteById(int id);


  List<HotelDTO> getHotelDTOByFilter(Filter filter);

  List<Hotel> getAllByOwner(String owner);

  Statistic getStatistic(Integer hotelId, Integer year);
}
