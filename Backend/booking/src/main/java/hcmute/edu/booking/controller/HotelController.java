package hcmute.edu.booking.controller;


import hcmute.edu.booking.DTO.HotelDTO;
import hcmute.edu.booking.filter.Filter;
import hcmute.edu.booking.model.Hotel;
import hcmute.edu.booking.model.DataResponse;
import hcmute.edu.booking.model.Review;
import hcmute.edu.booking.model.Statistic;
import hcmute.edu.booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/Hotel")
public class HotelController {
  @Autowired
  private HotelService hotelService;

  @GetMapping("")
  DataResponse getAllHotel() {
    List<Hotel> listHotel = hotelService.getAll();
    return new DataResponse(listHotel);
  }
  @GetMapping("/{id}")
  DataResponse findHotelById(@PathVariable Integer id) {
    Optional<Hotel> foundHotel = hotelService.findById(id);
    if (foundHotel.isPresent())
      return new DataResponse(foundHotel);
    else
      throw new RuntimeException("Cannot find hotel with id = " + id);
  }

  @PostMapping("")
  DataResponse insertHotel(@RequestBody @Validated Hotel newHotel, BindingResult result) {
    if (!result.hasErrors()){
      return new DataResponse(hotelService.insert(newHotel));
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }
  @PutMapping("/{id}")
  DataResponse updateHotel(@RequestBody @Validated Hotel newHotel, BindingResult result, @PathVariable Integer id) {
    if (!result.hasErrors()){
      Hotel updateHotel = hotelService.update(newHotel, id);
      return new DataResponse(updateHotel);
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }
  @DeleteMapping("/{id}")
  DataResponse deleteHotel(@PathVariable Integer id) {
    boolean exists = hotelService.existsById(id);
    if (exists) {
      hotelService.deleteById(id);
      return new DataResponse("");
    }
    throw new RuntimeException("Cannot find hotel with id = " + id + " to delete");
  }
  @PostMapping("/filter")
  DataResponse getHotelDTOByFilter(@RequestBody Filter filter){
    List<HotelDTO> hotelDTOList = hotelService.getHotelDTOByFilter(filter);
    return new DataResponse(hotelDTOList);

  }
  @PostMapping("/owner")
  DataResponse getAllHotelByOwner(@RequestParam("owner") String owner) {
    List<Hotel> hotelList = hotelService.getAllByOwner(owner);
    return new DataResponse(hotelList);
  }
  @PostMapping("/statistic")
  DataResponse getStatisticByHotelIdAndYear(@RequestParam("hotelId") Integer hotelId,@RequestParam("year") Integer year){
    Statistic statistic = hotelService.getStatistic(hotelId,year);
    return new DataResponse(statistic);
  }


}

