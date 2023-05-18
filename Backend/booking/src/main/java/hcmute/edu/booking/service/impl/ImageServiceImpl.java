package hcmute.edu.booking.service.impl;

import hcmute.edu.booking.model.Image;
import hcmute.edu.booking.repository.ImageRepository;
import hcmute.edu.booking.service.ImageService;
import hcmute.edu.booking.service.StorageService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
  
  @Autowired
  private ImageRepository imageRepository;

  @Autowired
  private StorageService storageService;

  @Override
  public List<Image> getAll() {
    return imageRepository.findAll();
  }

  @Override
  public Optional<Image> findById(int id) {
    return imageRepository.findById(id);
  }


  @Override
  public Image insert(Image review) {
    return imageRepository.save(review);
  }

  @Override
  public Image update(Image newImage, int id) {
    return imageRepository
        .findById(id)
        .map(
            image -> {
              image.setHotelId(newImage.getHotelId());
              image.setRoomId(newImage.getRoomId());
              image.setLink(newImage.getLink());
              return imageRepository.save(image);
            })
        .orElseGet(
            () -> imageRepository.save(newImage));
  }
  @Override
  public boolean existsById(int id) {
    return imageRepository.existsById(id);
  }

  @Override
  public void deleteById(int id) {
    imageRepository.deleteById(id);
  }

  @Override
  public Object uploadImage(Integer hotelId, Integer roomId, MultipartFile file) {
    if (!storageService.isImage(file)) {
      throw new RuntimeException("The file is not an image");
    }
    String random = RandomStringUtils.randomAlphanumeric(6);

    String url = storageService.uploadImage(file, "npbooking/" + hotelId + "_" + roomId+"_"+ random);

    if (url.equals("")) {
      throw new RuntimeException("Fail to upload image");
    }
    Image image = new Image(hotelId,roomId,url);
    imageRepository.save(image);
    return null;
  }

  @Override
  public List<Image> getAllByHotelId(Integer hotelId) {
    return imageRepository.findAllByHotelId(hotelId);
  }

  @Override
  public List<Image> getAllByHotelIdAndRoomId(Integer hotelId, Integer roomId) {
    return imageRepository.findAllByHotelIdAndRoomId(hotelId,roomId);
  }
}
