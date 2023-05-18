package hcmute.edu.booking.service.impl;

import hcmute.edu.booking.model.Hotel;
import hcmute.edu.booking.model.Province;
import hcmute.edu.booking.repository.ProvinceRepository;
import hcmute.edu.booking.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {


  @Autowired
  private ProvinceRepository provinceRepository;
  @Override
  public List<Province> getAll() {
    return provinceRepository.findAll();
  }

  @Override
  public Optional<Province> findById(Integer id) {
    return provinceRepository.findById(id);
  }

  @Override
  public Object insert(Province newProvince) {
    return provinceRepository.save(newProvince);
  }
}
