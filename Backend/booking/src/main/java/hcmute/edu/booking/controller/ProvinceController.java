package hcmute.edu.booking.controller;

import hcmute.edu.booking.model.DataResponse;
import hcmute.edu.booking.model.Hotel;
import hcmute.edu.booking.model.Image;
import hcmute.edu.booking.model.Province;
import hcmute.edu.booking.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/Province")
public class ProvinceController {

  @Autowired
  private ProvinceService provinceService;

  @GetMapping("")
  DataResponse getAllProvince() {
    List<Province> listHotel = provinceService.getAll();
    return new DataResponse(listHotel);
  }
  @GetMapping("/{id}")
  DataResponse findProvinceById(@PathVariable Integer id) {
    Optional<Province> foundProvince = provinceService.findById(id);
    if (foundProvince.isPresent())
      return new DataResponse(foundProvince);
    else
      throw new RuntimeException("Cannot find province with id = " + id);
  }
  @PostMapping("")
  DataResponse insertProvince(@RequestBody @Validated Province newProvince, BindingResult result) {
    if (!result.hasErrors()){
      return new DataResponse(provinceService.insert(newProvince));
    }
    else {
      throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
    }
  }

}
