package hcmute.edu.booking.exception;

import hcmute.edu.booking.model.DataResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
  @ResponseBody
  public DataResponse handleRuntimeException(RuntimeException e){
    return new DataResponse("400",e.getMessage(),200);
  }
}
