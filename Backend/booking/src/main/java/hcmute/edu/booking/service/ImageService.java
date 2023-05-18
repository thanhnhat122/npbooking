package hcmute.edu.booking.service;

import hcmute.edu.booking.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ImageService {
  List<Image> getAll();

  Optional<Image> findById(int id);

  Object insert(Image newImage);

  Image update(Image newImage, int id);

  boolean existsById(int id);

  void deleteById(int id);

  Object uploadImage(Integer hotelId, Integer roomId, MultipartFile file);

  List<Image> getAllByHotelId(Integer hotelId);

  List<Image> getAllByHotelIdAndRoomId(Integer hotelId, Integer roomId);
}
