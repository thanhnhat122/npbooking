package hcmute.edu.booking.service.impl;

import hcmute.edu.booking.model.Review;
import hcmute.edu.booking.repository.ReviewRepository;
import hcmute.edu.booking.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
  @Autowired
  private ReviewRepository reviewRepository;

  @Override
  public List<Review> getAll() {
    return reviewRepository.findAll();
  }

  @Override
  public Optional<Review> findById(int id) {
    return reviewRepository.findById(id);
  }


  @Override
  public Review insert(Review review) {
    return reviewRepository.save(review);
  }

  @Override
  public Review update(Review newReview, int id) {
    return reviewRepository
        .findById(id)
        .map(
            review -> {
              review.setHotelId(newReview.getHotelId());
              review.setUserEmail(newReview.getUserEmail());
              review.setComment(newReview.getComment());
              review.setRate(newReview.getRate());
              return reviewRepository.save(review);
            })
        .orElseGet(
            () -> reviewRepository.save(newReview));
  }
  @Override
  public boolean existsById(int id) {
    return reviewRepository.existsById(id);
  }

  @Override
  public void deleteById(int id) {
    reviewRepository.deleteById(id);
  }

  @Override
  public List<Review> getAllByHotelId(Integer hotelId) {
    return reviewRepository.findAllByHotelId(hotelId);
  }
}
