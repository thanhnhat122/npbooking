package hcmute.edu.booking.service;

import hcmute.edu.booking.DTO.RoomDTO;
import hcmute.edu.booking.model.Review;
import hcmute.edu.booking.model.Room;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoomService {
  List<Room> getAll();

  Optional<Room> findById(Integer id);

  Room insert(Room newRoom);

  Room update(Room newRoom, Integer id);

  boolean existsById(Integer id);

  void deleteById(Integer id);

  List<RoomDTO> getAllRoomDTOByHotelId(Integer hotelId, Date dateIn, Date dateOut);

  List<Room> findAllByHotelId(Integer hotelId);
}
