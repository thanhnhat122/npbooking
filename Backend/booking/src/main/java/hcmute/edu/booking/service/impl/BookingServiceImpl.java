package hcmute.edu.booking.service.impl;

import hcmute.edu.booking.DTO.BookingDTO;
import hcmute.edu.booking.DTO.HotelDTO;
import hcmute.edu.booking.model.Booking;
import hcmute.edu.booking.model.Image;
import hcmute.edu.booking.repository.BookingRepository;
import hcmute.edu.booking.repository.HotelRepository;
import hcmute.edu.booking.repository.ImageRepository;
import hcmute.edu.booking.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
  @Autowired
  private BookingRepository bookingRepository;

  @Autowired
  private HotelRepository hotelRepository;
  @Autowired
  private ImageRepository imageRepository;

  @Autowired
  private ModelMapper modelMapper;
  @Override
  public List<Booking> getAll() {
    return bookingRepository.findAll();
  }

  @Override
  public Optional<Booking> findById(int id) {
    return bookingRepository.findById(id);
  }


  @Override
  public Booking insert(Booking booking) {
    return bookingRepository.save(booking);
  }

  @Override
  public Booking update(Booking newBooking, int id) {
    return bookingRepository
        .findById(id)
        .map(
            booking -> {
              booking.setDateIn(newBooking.getDateOut());
              booking.setDateOut(newBooking.getDateOut());
              booking.setUserEmail(newBooking.getUserEmail());
              booking.setSumPrice(newBooking.getSumPrice());
              booking.setStatus(newBooking.getStatus());
              return bookingRepository.save(booking);
            })
        .orElseGet(
            () -> bookingRepository.save(newBooking));
  }
  @Override
  public boolean existsById(int id) {
    return bookingRepository.existsById(id);
  }

  @Override
  public void deleteById(int id) {
    bookingRepository.deleteById(id);
  }

  @Override
  public List<Booking> getAllBookingByHotelId(Integer hotelId) {
    return bookingRepository.findAllByHotelId(hotelId);
  }

  @Override
  public List<BookingDTO> getAllBookingByUserEmail(String userEmail) {
    List<BookingDTO> bookingDTOList = bookingRepository.findAllByUserEmail(userEmail).stream().map(booking -> modelMapper.map(booking, BookingDTO.class)).collect(Collectors.toList());
    for(BookingDTO bookingDTO : bookingDTOList){
      bookingDTO.setHotel(hotelRepository.findHotelById(bookingDTO.getHotelId()));
      List<Image> imageList = imageRepository.findAllByHotelIdAndRoomId(bookingDTO.getHotelId(),0);
      if(imageList.size()>0)
        bookingDTO.setImage(imageList.get(0).getLink());
    }
    return bookingDTOList;
  }

  @Override
  public List<BookingDTO> getAllBookingByUserEmailAndStatus(String userEmail, String status) {
    List<BookingDTO> bookingDTOList = bookingRepository.findAllByUserEmailAndStatus(userEmail,status).stream().map(booking -> modelMapper.map(booking, BookingDTO.class)).collect(Collectors.toList());
    for(BookingDTO bookingDTO : bookingDTOList){
      bookingDTO.setHotel(hotelRepository.findHotelById(bookingDTO.getHotelId()));
      List<Image> imageList = imageRepository.findAllByHotelIdAndRoomId(bookingDTO.getHotelId(),0);
      if(imageList.size()>0)
        bookingDTO.setImage(imageList.get(0).getLink());
    }
    return bookingDTOList;
  }

  @Override
  public boolean checkBooking(Integer bookingId) {
    Booking booking = bookingRepository.findBookingById(bookingId);
    Date date = new Date();
    long temp = booking.getDateOut().getTime() - date.getTime();
    if((temp / ( 24 * 60 * 60 * 1000)) >= 5)
    {
      booking.setStatus("Đã hủy");
      bookingRepository.save(booking);
      return true;
    }
    return false;
  }

}
