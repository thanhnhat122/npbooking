package hcmute.edu.booking.repository;

import hcmute.edu.booking.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
  List<Image> findAllByHotelIdAndRoomId(int hotelId, int roomId);

  List<Image> findAllByHotelId(Integer hotelId);
}
