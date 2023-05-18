package hcmute.edu.booking.controller;

import hcmute.edu.booking.model.DataResponse;
import hcmute.edu.booking.model.Review;
import hcmute.edu.booking.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/Review")
public class ReviewController {
  @Autowired
  private ReviewService reviewService;

  @GetMapping("")
  DataResponse getAllReview() {
    List<Review> listReview = reviewService.getAll();
    return new DataResponse(listReview);
  }

  @GetMapping("/{id}")
  DataResponse findReviewById(@PathVariable Integer id) {
    Optional<Review> foundReview = reviewService.findById(id);
    if (foundReview.isPresent())
      return new DataResponse(foundReview);
    else
      throw new RuntimeException("Cannot find review with id = " + id);
  }
  @PostMapping("/hotel")
  DataResponse getAllReviewByHotelId(@RequestParam("hotelId") Integer hotelId) {
    List<Review> listReview = reviewService.getAllByHotelId(hotelId);
    return new DataResponse(listReview);
  }
  @PostMapping("")
  DataResponse insertReview(@RequestBody @Validated Review newReview, BindingResult result) {
    if (!result.hasErrors()){
      return new DataResponse(reviewService.insert(newReview));
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }
  @PutMapping("/{id}")
  DataResponse updateReview(@RequestBody @Validated Review newReview, BindingResult result, @PathVariable Integer id) {
    if (!result.hasErrors()){
      Review updateReview = reviewService.update(newReview, id);
      return new DataResponse(updateReview);
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }
  @DeleteMapping("/{id}")
  DataResponse deleteReview(@PathVariable Integer id) {
    boolean exists = reviewService.existsById(id);
    if (exists) {
      reviewService.deleteById(id);
      return new DataResponse("");
    }
    throw new RuntimeException("Cannot find review with id = " + id + " to delete");
  }


}
