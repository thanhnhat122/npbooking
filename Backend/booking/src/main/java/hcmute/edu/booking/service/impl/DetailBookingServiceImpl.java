package hcmute.edu.booking.service.impl;

import hcmute.edu.booking.DTO.DetailBookingDTO;
import hcmute.edu.booking.DTO.HotelDTO;
import hcmute.edu.booking.model.DetailBooking;
import hcmute.edu.booking.repository.BookingRepository;
import hcmute.edu.booking.repository.DetailBookingRepository;
import hcmute.edu.booking.repository.RoomRepository;
import hcmute.edu.booking.service.DetailBookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetailBookingServiceImpl implements DetailBookingService {

  @Autowired
  private DetailBookingRepository detailBookingRepository;
  @Autowired
  private RoomRepository roomRepository;
  @Autowired
  private BookingRepository bookingRepository;
  @Autowired
  private ModelMapper modelMapper;
  @Override
  public List<DetailBooking> getAll() {
    return detailBookingRepository.findAll();
  }

  @Override
  public Optional<DetailBooking> findById(Integer id) {
    return detailBookingRepository.findById(id);
  }

  @Override
  public DetailBooking insert(DetailBooking newDetailBooking) {
    return detailBookingRepository.save(newDetailBooking);
  }

  @Override
  public DetailBooking update(DetailBooking newDetailBooking, Integer id) {
    return detailBookingRepository
        .findById(id)
        .map(
            detailBooking -> {
              detailBooking.setBookingId(newDetailBooking.getBookingId());
              detailBooking.setRoomId(newDetailBooking.getRoomId());
              detailBooking.setQuantity(newDetailBooking.getQuantity());
              return detailBookingRepository.save(detailBooking);
            })
        .orElseGet(
            () -> detailBookingRepository.save(newDetailBooking));
  }

  @Override
  public boolean existsById(Integer id) {
    return detailBookingRepository.existsById(id);
  }

  @Override
  public void deleteById(Integer id) {
    detailBookingRepository.deleteById(id);
  }

  @Override
  public List<DetailBookingDTO> getAllDetailBookingByBookingId(Integer bookingId) {
    List<DetailBookingDTO> detailBookingDTOS = detailBookingRepository.findAllByBookingId(bookingId).stream().map(detailBooking ->modelMapper.map(detailBooking,DetailBookingDTO.class)).collect(Collectors.toList());
    for(DetailBookingDTO detailBookingDTO : detailBookingDTOS){
      detailBookingDTO.setRoom(roomRepository.findRoomById(detailBookingDTO.getRoomId()));
    }
    return  detailBookingDTOS;
  }
  public Integer getAllDetailBookingByRoomId(Integer roomId, Date dateIn, Date dateOut){
    List<DetailBookingDTO> detailBookingDTOS = detailBookingRepository.findAllByRoomId(roomId).stream().map(detailBooking ->modelMapper.map(detailBooking,DetailBookingDTO.class)).collect(Collectors.toList());
    if(detailBookingDTOS.size()>0){
      List<DetailBookingDTO> detailBookingListRemove = new ArrayList<DetailBookingDTO>();
      for(DetailBookingDTO detailBookingDTO : detailBookingDTOS){
        detailBookingDTO.setBooking(bookingRepository.findBookingById(detailBookingDTO.getBookingId()));
        if(checkDate(detailBookingDTO,dateIn,dateOut)){
          detailBookingListRemove.add(detailBookingDTO);
        }
      }
      removeDetailBookingDTO(detailBookingDTOS,detailBookingListRemove);
    }
    return countRoom(detailBookingDTOS);

  }
  public int countRoom( List<DetailBookingDTO> detailBookingDTOS){
    int count = 0;
    for(DetailBookingDTO detailBookingDTO : detailBookingDTOS){
      count += detailBookingDTO.getQuantity();
    }
    return count;
  }


  public boolean checkDate(DetailBookingDTO detailBookingDTO,Date dateIn, Date dateOut){
    return dateIn.getTime() > detailBookingDTO.getBooking().getDateOut().getTime() || dateOut.getTime() < detailBookingDTO.getBooking().getDateIn().getTime();
  }

  public void removeDetailBookingDTO(List<DetailBookingDTO> detailBookingDTOS,List<DetailBookingDTO> detailBookingListRemove ){
    for (DetailBookingDTO detailBookingDTO: detailBookingListRemove){
      detailBookingDTOS.remove(detailBookingDTO);
    }
  }

}
