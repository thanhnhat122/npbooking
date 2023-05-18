package hcmute.edu.booking.controller;

import hcmute.edu.booking.model.DataResponse;
import hcmute.edu.booking.model.Image;
import hcmute.edu.booking.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/Image")
public class ImageController {
  @Autowired
  private ImageService imageService;

  @GetMapping("")
  DataResponse getAllImage() {
    List<Image> listImage = imageService.getAll();
    return new DataResponse(listImage);
  }
  @GetMapping("/{id}")
  DataResponse findImageById(@PathVariable Integer id) {
    Optional<Image> foundImage = imageService.findById(id);
    if (foundImage.isPresent())
      return new DataResponse(foundImage);
    else
      throw new RuntimeException("Cannot find image with id = " + id);
  }
  @PostMapping("")
  DataResponse insertImage(@RequestBody @Validated Image newImage, BindingResult result) {
    if (!result.hasErrors()){
      return new DataResponse(imageService.insert(newImage));
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }
  @PostMapping("/getAllImageHotel")
  DataResponse getAllImageHotel(@RequestParam("hotelId") Integer hotelId){
    List<Image> listImage = imageService.getAllByHotelId(hotelId);
    return new DataResponse(listImage);
  }
  @PostMapping("/getAllImageHotelRoom")
  DataResponse getAllImageHotelRoom(@RequestParam("hotelId") Integer hotelId, @RequestParam("roomId") Integer roomId){
    List<Image> listImage = imageService.getAllByHotelIdAndRoomId(hotelId,roomId);
    return new DataResponse(listImage);
  }

  @PostMapping("/uploadImage")
  DataResponse saveImage(
      @RequestParam("hotelId") Integer hotelId, @RequestParam("roomId") Integer roomId, @RequestParam("file") MultipartFile file) {
    return new DataResponse(imageService.uploadImage(hotelId, roomId, file));
  }

  @PutMapping("/{id}")
  DataResponse updateImage(@RequestBody @Validated Image newImage, BindingResult result, @PathVariable Integer id) {
    if (!result.hasErrors()){
      Image updateImage = imageService.update(newImage, id);
      return new DataResponse(updateImage);
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }
  @DeleteMapping("/{id}")
  DataResponse deleteImage(@PathVariable Integer id) {
    boolean exists = imageService.existsById(id);
    if (exists) {
      imageService.deleteById(id);
      return new DataResponse("");
    }
    throw new RuntimeException("Cannot find image with id = " + id + " to delete");
  }

}
