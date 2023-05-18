package hcmute.edu.booking.service;

import com.cloudinary.Cloudinary;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
  Cloudinary cloudinary();

  boolean isImage(MultipartFile file);

  String uploadImage(MultipartFile file, String filePath);
}
