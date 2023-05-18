package hcmute.edu.booking.controller;

import hcmute.edu.booking.DTO.DetailBookingDTO;
import hcmute.edu.booking.model.DetailBooking;
import hcmute.edu.booking.model.DataResponse;
import hcmute.edu.booking.service.DetailBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/DetailBooking")
public class DetailBookingController {
  @Autowired
  private DetailBookingService detailBookingService;

  @GetMapping("")
  DataResponse getAllDetailBooking() {
    List<DetailBooking> listDetailBooking = detailBookingService.getAll();
    return new DataResponse(listDetailBooking);
  }
  @GetMapping("/{id}")
  DataResponse findDetailBookingById(@PathVariable Integer id) {
    Optional<DetailBooking> foundDetailBooking = detailBookingService.findById(id);
    if (foundDetailBooking.isPresent())
      return new DataResponse(foundDetailBooking);
    else
      throw new RuntimeException("Cannot find detail booking with id = " + id);
  }
  @GetMapping("/booking/{bookingId}")
  DataResponse findDetailBookingDTOByBookingId(@PathVariable Integer bookingId) {
    List<DetailBookingDTO> detailBookingDTOS = detailBookingService.getAllDetailBookingByBookingId(bookingId);
    return new DataResponse(detailBookingDTOS);
  }
  @PostMapping("")
  DataResponse insertDetailBooking(@RequestBody @Validated DetailBooking newDetailBooking, BindingResult result) {
    if (!result.hasErrors()){
      return new DataResponse(detailBookingService.insert(newDetailBooking));
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }
  @PutMapping("/{id}")
  DataResponse updateDetailBooking(@RequestBody @Validated DetailBooking newDetailBooking, BindingResult result, @PathVariable Integer id) {
    if (!result.hasErrors()){
      DetailBooking updateDetailBooking = detailBookingService.update(newDetailBooking, id);
      return new DataResponse(updateDetailBooking);
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }
  @DeleteMapping("/{id}")
  DataResponse deleteDetailBooking(@PathVariable Integer id) {
    boolean exists = detailBookingService.existsById(id);
    if (exists) {
      detailBookingService.deleteById(id);
      return new DataResponse("");
    }
    throw new RuntimeException("Cannot find detail booking with id = " + id + " to delete");
  }
  
}
