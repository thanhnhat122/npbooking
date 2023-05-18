package hcmute.edu.booking.controller;

import hcmute.edu.booking.DTO.RoomDTO;
import hcmute.edu.booking.model.DataResponse;
import hcmute.edu.booking.model.Review;
import hcmute.edu.booking.model.Room;
import hcmute.edu.booking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/Room")
public class RoomController {

  @Autowired
  private RoomService roomService;

  @GetMapping("")
  DataResponse getAllRoom() {
    List<Room> listRoom = roomService.getAll();
    return new DataResponse(listRoom);
  }

  @GetMapping("/{id}")
  DataResponse findRoomById(@PathVariable Integer id) {
    Optional<Room> foundRoom = roomService.findById(id);
    if (foundRoom.isPresent())
      return new DataResponse(foundRoom);
    else
      throw new RuntimeException("Cannot find room with id = " + id);
  }
  @GetMapping("/hotel/{hotelId}")
  DataResponse findRoomByHotelId(@PathVariable Integer hotelId) {
    List<Room> listRoom = roomService.findAllByHotelId(hotelId);
      return new DataResponse(listRoom);
  }
  @PostMapping("/roomDTO")
  DataResponse getAllRoomDTOByHotelId(@RequestParam("hotelId") Integer hotelId, @RequestParam("dateIn") String dateIn, @RequestParam("dateOut") String dateOut) throws ParseException {
    Date dateIn1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateIn);
    Date dateOut1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateOut);
    List<RoomDTO> roomDTOList = roomService.getAllRoomDTOByHotelId(hotelId,dateIn1,dateOut1);
    return new DataResponse(roomDTOList);
  }
  @PostMapping("")
  DataResponse insertRoom(@RequestBody @Validated Room newRoom, BindingResult result) {
    if (!result.hasErrors()){
      return new DataResponse(roomService.insert(newRoom));
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }

  @PutMapping("/{id}")
  DataResponse updateRoom(@RequestBody @Validated Room newRoom, BindingResult result, @PathVariable Integer id) {
    if (!result.hasErrors()){
      Room updateRoom = roomService.update(newRoom, id);
      return new DataResponse(updateRoom);
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }

  @DeleteMapping("/{id}")
  DataResponse deleteRoom(@PathVariable Integer id) {
    boolean exists = roomService.existsById(id);
    if (exists) {
      roomService.deleteById(id);
      return new DataResponse("");
    }
    throw new RuntimeException("Cannot find room with id = " + id + " to delete");
  }


}
