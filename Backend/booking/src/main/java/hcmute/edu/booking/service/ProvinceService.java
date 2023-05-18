package hcmute.edu.booking.service;

import hcmute.edu.booking.model.Hotel;
import hcmute.edu.booking.model.Province;

import java.util.List;
import java.util.Optional;

public interface ProvinceService {
  List<Province> getAll();

  Optional<Province> findById(Integer id);

  Object insert(Province newProvince);
}
