package hcmute.edu.booking.service.impl;

import hcmute.edu.booking.DTO.HotelDTO;
import hcmute.edu.booking.filter.Filter;
import hcmute.edu.booking.model.*;
import hcmute.edu.booking.repository.*;
import hcmute.edu.booking.service.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {
  @Autowired
  private HotelRepository hotelRepository;

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private ImageRepository imageRepository;
  @Autowired
  private DetailBookingServiceImpl detailBookingService;
  @Autowired
  private BookingRepository bookingRepository;

  @Autowired
  private ModelMapper modelMapper;
  @Override
  public List<Hotel> getAll() {
    return hotelRepository.findAll();
  }

  @Override
  public Optional<Hotel> findById(int id) {
    return hotelRepository.findById(id);
  }


  @Override
  public Hotel insert(Hotel review) {
    return hotelRepository.save(review);
  }

  @Override
  public Hotel update(Hotel newHotel, int id) {
    return hotelRepository
        .findById(id)
        .map(
            hotel -> {
              hotel.setName(newHotel.getName());
              hotel.setDescription(newHotel.getDescription());
              hotel.setProvince(newHotel.getProvince());
              hotel.setAddress(newHotel.getAddress());
              hotel.setBenefit(newHotel.getBenefit());
              hotel.setStar(newHotel.getStar());
              hotel.setBreakfast(newHotel.isBreakfast());
              hotel.setDistanceCenter(newHotel.getDistanceCenter());
              hotel.setSustainTour(newHotel.isSustainTour());
              hotel.setSea(newHotel.isSea());
              return hotelRepository.save(hotel);
            })
        .orElseGet(
            () -> hotelRepository.save(newHotel));
  }
  @Override
  public boolean existsById(int id) {
    return hotelRepository.existsById(id);
  }

  @Override
  public void deleteById(int id) {
    hotelRepository.deleteById(id);
  }

  @Override
  public List<HotelDTO> getHotelDTOByFilter(Filter filter) {
    int numNight = getNumNight(filter.getDateIn(),filter.getDateOut());
    List<HotelDTO> hotelDTOList = hotelRepository.findAllByProvince(filter.getProvince()).stream().map(hotel -> modelMapper.map(hotel,HotelDTO.class)).collect(Collectors.toList());
    if(filter.getStar().size()>0)
    {
      getStarHotel(hotelDTOList,filter.getStar());
    }
    if(filter.isSustainTour())
    {
      getSustainTour(hotelDTOList);
    }
    if(filter.isSea())
    {
      getSea(hotelDTOList);
    }
    getScoreHotel(hotelDTOList);
    if(filter.getScore() != -1)
    {
      checkScoreHotel(hotelDTOList,filter.getScore());
    }
    if(filter.getDistanceCenter() != -1)
    {
      checkDistanceCenter(hotelDTOList,filter.getDistanceCenter());
    }
    getRoom(hotelDTOList,filter.getNumPeople(),filter.getPriceMin(),filter.getPriceMax(),filter.getDateIn(),filter.getDateOut());
    getImage(hotelDTOList);
    for (HotelDTO hotelDTO : hotelDTOList) {
      hotelDTO.setNumNight(numNight);
    }

    return hotelDTOList;
  }

  @Override
  public List<Hotel> getAllByOwner(String owner) {
    return hotelRepository.findAllByOwner(owner);
  }

  @Override
  public Statistic getStatistic(Integer hotelId, Integer year) {
    String status = "Hoàn tất";
    List<Booking> bookingList = bookingRepository.findAllByStatusAndHotelId(status,hotelId);
    Statistic statistic = new Statistic(0,0,0,0,0,0,0,0,0,0,0,0,0);
    setStatistic(statistic,bookingList,year);
    return statistic;
  }
  public void setStatistic (Statistic statistic, List<Booking> bookingList,Integer year){

    for(Booking booking: bookingList){
      int month = booking.getDateOut().getMonth()+1;
      int yearBooking = booking.getDateOut().getYear()+1900;
      System.out.println(yearBooking);
      if(yearBooking == year) {
        if (month == 1) {
          statistic.setMonth1(statistic.getMonth1() + booking.getSumPrice());
        }
        if (month == 2) {
          statistic.setMonth2(statistic.getMonth2() + booking.getSumPrice());
        }
        if (month == 3) {
          statistic.setMonth3(statistic.getMonth3() + booking.getSumPrice());
        }
        if (month == 4) {
          statistic.setMonth4(statistic.getMonth4() + booking.getSumPrice());
        }
        if (month == 5) {
          statistic.setMonth5(statistic.getMonth5() + booking.getSumPrice());
        }
        if (month == 6) {
          statistic.setMonth6(statistic.getMonth6() + booking.getSumPrice());
        }
        if (month == 7) {
          statistic.setMonth7(statistic.getMonth7() + booking.getSumPrice());
        }
        if (month == 8) {
          statistic.setMonth8(statistic.getMonth8() + booking.getSumPrice());
        }
        if (month == 9) {
          statistic.setMonth9(statistic.getMonth9() + booking.getSumPrice());
        }
        if (month == 10) {
          statistic.setMonth10(statistic.getMonth10() + booking.getSumPrice());
        }
        if (month == 11) {
          statistic.setMonth11(statistic.getMonth11() + booking.getSumPrice());
        }
        if(month == 12) {
          statistic.setMonth12(statistic.getMonth12() + booking.getSumPrice());
        }
        statistic.setTotal(statistic.getTotal() + booking.getSumPrice());
      }
    }

  }

  public void getImage(List<HotelDTO> hotelDTOList){
    for (HotelDTO hotelDTO : hotelDTOList) {
      List<Image> imageList = imageRepository.findAllByHotelIdAndRoomId(hotelDTO.getId(),0);
      if(imageList.size()>0)
        hotelDTO.setImage(imageList.get(0).getLink());
    }


  }

  public void getRoom(List<HotelDTO> hotelDTOList,int numPeople,double priceMin,double priceMax,Date dateIn, Date dateOut){
    if(numPeople == -1)
      numPeople = 0;
    if(priceMin == -1)
      priceMin = 0;
    if(priceMax == -1)
      priceMax = 999999999;
    List<HotelDTO> hotelDTOListRemove = new ArrayList<HotelDTO>();
    List<Room> roomListRemove = new ArrayList<Room>();
    for (HotelDTO hotelDTO : hotelDTOList) {
      List<Room> roomList = roomRepository.findRoomByFilter(hotelDTO.getId(),numPeople,priceMin,priceMax);
      for(Room room : roomList){
        int count = detailBookingService.getAllDetailBookingByRoomId(room.getId(),dateIn,dateOut);
        if(count == room.getQuantity()){
          roomListRemove.add(room);
        }
        else room.setRemainRoom(room.getQuantity() - count );
      }
      removeRoom(roomList,roomListRemove);
      if (roomList.size()>0)
        hotelDTO.setRoom(roomList.get(0));
      else
        hotelDTOListRemove.add(hotelDTO);
    }
    removeHotelDTO(hotelDTOList,hotelDTOListRemove);
  }

  public void getScoreHotel(List<HotelDTO> hotelDTOList){
    for (HotelDTO hotelDTO : hotelDTOList) {
      List<Review> reviewList = reviewRepository.findAllByHotelId(hotelDTO.getId());
      int sum = 0;
      for(Review review: reviewList){
        sum = sum + review.getRate();
      }
      double score = (double) sum/reviewList.size();
      hotelDTO.setScore(score);
      hotelDTO.setNumReview(reviewList.size());
    }
  }
  public void checkDistanceCenter(List<HotelDTO> hotelDTOList,int distanceCenter){
    List<HotelDTO> hotelDTOListRemove = new ArrayList<HotelDTO>();
    for (HotelDTO hotelDTO : hotelDTOList) {
      if(hotelDTO.getDistanceCenter() > distanceCenter){
        hotelDTOListRemove.add(hotelDTO);
      }
    }
    removeHotelDTO(hotelDTOList,hotelDTOListRemove);
  }

  public void checkScoreHotel(List<HotelDTO> hotelDTOList,int score)
  {
    List<HotelDTO> hotelDTOListRemove = new ArrayList<HotelDTO>();
    for (HotelDTO hotelDTO : hotelDTOList) {
      if(hotelDTO.getScore() <= score){
        hotelDTOListRemove.add(hotelDTO);
      }
    }
    removeHotelDTO(hotelDTOList,hotelDTOListRemove);

  }
  public void getSustainTour(List<HotelDTO> hotelDTOList)
  {
    List<HotelDTO> hotelDTOListRemove = new ArrayList<HotelDTO>();
    for (HotelDTO hotelDTO : hotelDTOList) {
      if(!hotelDTO.isSustainTour()){
        hotelDTOListRemove.add(hotelDTO);
      }
    }
    removeHotelDTO(hotelDTOList,hotelDTOListRemove);

  }
  public void getSea(List<HotelDTO> hotelDTOList)
  {
    List<HotelDTO> hotelDTOListRemove = new ArrayList<HotelDTO>();
    for (HotelDTO hotelDTO : hotelDTOList) {
      if(!hotelDTO.isSea()){
        hotelDTOListRemove.add(hotelDTO);
      }
    }
    removeHotelDTO(hotelDTOList,hotelDTOListRemove);

  }
  public int getNumNight(Date startDate, Date endDate){
    long startValue = startDate.getTime();
    long endValue = endDate.getTime();
    long tmp = endValue - startValue;
    return (int)tmp/(24*60*60*1000);
  }
  public void getStarHotel(List<HotelDTO> hotelDTOList, List<Integer> listStar)
  {
    List<HotelDTO> hotelDTOListRemove = new ArrayList<HotelDTO>();
    System.out.println(hotelDTOList);
    for (HotelDTO hotelDTO : hotelDTOList) {
      int tmp = 0;
      for (Integer integer : listStar) {
        if (hotelDTO.getStar() == integer) {
          tmp = 1;
          break;
        }
      }
      if (tmp == 0) {
        hotelDTOListRemove.add(hotelDTO);
      }
    }
    removeHotelDTO(hotelDTOList,hotelDTOListRemove);

  }
  public void removeHotelDTO(List<HotelDTO> hotelDTOList,List<HotelDTO> hotelDTOListRemove ){
    for (HotelDTO hotelDTO: hotelDTOListRemove){
      hotelDTOList.remove(hotelDTO);
    }
  }
  public void removeRoom(List<Room> roomList,List<Room> roomListRemove ){
    for (Room room: roomListRemove){
      roomList.remove(room);
    }
  }




}
