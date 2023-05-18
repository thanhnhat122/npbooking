package hcmute.edu.booking.repository;

import hcmute.edu.booking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(value = "SELECT * FROM room r WHERE r.hotel_id = :hotelId and r.people_number >= :peopleNumber and r.price > :priceMin and r.price < :priceMax ORDER BY r.price", nativeQuery = true)
    List<Room> findRoomByFilter(@Param("hotelId") Integer hotelId, @Param("peopleNumber") Integer peopleNumber, @Param("priceMin") Double priceMin, @Param("priceMax") Double priceMax);


    List<Room> findAllByHotelIdOrderByPrice(Integer hotelId);

  Room findRoomById(int roomId);
}
