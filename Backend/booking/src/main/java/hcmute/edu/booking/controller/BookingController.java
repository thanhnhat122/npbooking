package hcmute.edu.booking.controller;

import hcmute.edu.booking.DTO.BookingDTO;
import hcmute.edu.booking.model.Booking;
import hcmute.edu.booking.model.DataResponse;
import hcmute.edu.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/Booking")
public class BookingController {
  @Autowired
  private BookingService bookingService;

  @GetMapping("")
  DataResponse getAllBooking() {
    List<Booking> listBooking = bookingService.getAll();
    return new DataResponse(listBooking);
  }
  @GetMapping("/{id}")
  DataResponse findBookingById(@PathVariable Integer id) {
    Optional<Booking> foundBooking = bookingService.findById(id);
    if (foundBooking.isPresent())
      return new DataResponse(foundBooking);
    else
      throw new RuntimeException("Cannot find booking with id = " + id);
  }
  @GetMapping("hotelId/{hotelId}")
  DataResponse findAllBookingByHotelId(@PathVariable Integer hotelId) {
    List<Booking> listBooking = bookingService.getAllBookingByHotelId(hotelId);
    return new DataResponse(listBooking);
  }
  @PostMapping("")
  DataResponse insertBooking(@RequestBody @Validated Booking newBooking, BindingResult result) {
    if (!result.hasErrors()){
      return new DataResponse(bookingService.insert(newBooking));
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }
  @PutMapping("/{id}")
  DataResponse updateBooking(@RequestBody @Validated Booking newBooking, BindingResult result, @PathVariable Integer id) {
    if (!result.hasErrors()){
      Booking updateBooking = bookingService.update(newBooking, id);
      return new DataResponse(updateBooking);
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }
  @DeleteMapping("/{id}")
  DataResponse deleteBooking(@PathVariable Integer id) {
    boolean exists = bookingService.existsById(id);
    if (exists) {
      bookingService.deleteById(id);
      return new DataResponse("");
    }
    throw new RuntimeException("Cannot find booking with id = " + id + " to delete");
  }
  @PostMapping("/email")
  DataResponse getBookingDTOByEmail(@RequestParam String userEmail) {
    List<BookingDTO> bookingDTOList = bookingService.getAllBookingByUserEmail(userEmail);
    return new DataResponse(bookingDTOList);
  }
  @PostMapping("/status")
  DataResponse getBookingDTOByEmailAndStatus(@RequestParam String userEmail,@RequestParam String status) {
    List<BookingDTO> bookingDTOList = bookingService.getAllBookingByUserEmailAndStatus(userEmail,status);
    return new DataResponse(bookingDTOList);
  }
  @PostMapping("/cancel")
  DataResponse cancelBooking (@RequestParam Integer bookingId) {
    if(bookingService.checkBooking(bookingId))
      return new DataResponse("Hủy đặt phòng thành công");
    else
      return new DataResponse("Đã hết hạn hủy đặt phòng");
  }
}
