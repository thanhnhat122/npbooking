package hcmute.edu.booking.service;

import hcmute.edu.booking.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
  List<Review> getAll();

  Optional<Review> findById(int id);


  Review insert(Review review);

  Review update(Review review, int id);

  boolean existsById(int id);

  void deleteById(int id);

  List<Review> getAllByHotelId(Integer hotelId);
}
