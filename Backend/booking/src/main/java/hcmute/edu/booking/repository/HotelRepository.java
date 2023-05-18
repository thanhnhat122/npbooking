package hcmute.edu.booking.repository;

import hcmute.edu.booking.DTO.HotelDTO;
import hcmute.edu.booking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
  List<Hotel> findAllByProvince(int province);


  List<Hotel> findAllByOwner(String owner);

  Hotel findHotelById(int hotelId);
}
