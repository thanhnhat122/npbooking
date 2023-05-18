package hcmute.edu.booking.service.impl;

import hcmute.edu.booking.DTO.HotelDTO;
import hcmute.edu.booking.DTO.RoomDTO;
import hcmute.edu.booking.model.Review;
import hcmute.edu.booking.model.Room;
import hcmute.edu.booking.repository.RoomRepository;
import hcmute.edu.booking.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

  @Autowired
  private RoomRepository roomRepository;
  @Autowired
  private DetailBookingServiceImpl detailBookingService;

  @Autowired
  private ModelMapper modelMapper;
  @Override
  public List<Room> getAll() {
    return roomRepository.findAll();
  }

  @Override
  public Optional<Room> findById(Integer id) {
    return roomRepository.findById(id);
  }

  @Override
  public Room insert(Room newRoom) {
    return roomRepository.save(newRoom);
  }

  @Override
  public Room update(Room newRoom, Integer id) {
    return roomRepository
        .findById(id)
        .map(
            room -> {
              room.setHotelId(newRoom.getHotelId());
              room.setSize(newRoom.getSize());
              room.setPrice(newRoom.getPrice());
              room.setPeopleNumber(newRoom.getPeopleNumber());
              room.setDiscount(newRoom.getDiscount());
              room.setSingleBed(newRoom.getSingleBed());
              room.setDoubleBed(newRoom.getDoubleBed());
              room.setQuantity(newRoom.getQuantity());
              room.setRemainRoom(newRoom.getRemainRoom());
              room.setType(newRoom.getType());
              room.setBenefitRoom(newRoom.getBenefitRoom());
              room.setBathRoom(newRoom.getBathRoom());
              room.setView(newRoom.getView());
              room.setConvenient(newRoom.getConvenient());
              room.setSmoking(newRoom.isSmoking());

              return roomRepository.save(room);
            })
        .orElseGet(
            () -> roomRepository.save(newRoom));
  }

  @Override
  public boolean existsById(Integer id) {
    return roomRepository.existsById(id);
  }

  @Override
  public void deleteById(Integer id) {
    roomRepository.deleteById(id);

  }

  @Override
  public List<RoomDTO> getAllRoomDTOByHotelId(Integer hotelId, Date dateIn, Date dateOut) {
    int numNight = getNumNight(dateIn,dateOut);
    List<RoomDTO> roomDTOList = roomRepository.findAllByHotelIdOrderByPrice(hotelId).stream().map(room -> modelMapper.map(room,RoomDTO.class)).collect(Collectors.toList());
    for(RoomDTO roomDTO: roomDTOList){
      roomDTO.setNumNight(numNight);
      int count = detailBookingService.getAllDetailBookingByRoomId(roomDTO.getId(),dateIn,dateOut);
      roomDTO.setRemainRoom(roomDTO.getQuantity()-count);
    }
    return roomDTOList;
  }

  @Override
  public List<Room> findAllByHotelId(Integer hotelId) {
    return roomRepository.findAllByHotelIdOrderByPrice(hotelId);
  }

  public int getNumNight(Date startDate, Date endDate){
    long startValue = startDate.getTime();
    long endValue = endDate.getTime();
    long tmp = endValue - startValue;
    return (int)tmp/(24*60*60*1000);
  }

}
