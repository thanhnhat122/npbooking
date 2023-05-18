package hcmute.edu.booking.repository;

import hcmute.edu.booking.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
  List<Review> findByHotelId(int id);

  List<Review> findAllByHotelId(int hotelId);
}
