package hcmute.edu.booking.DTO;

import hcmute.edu.booking.model.Hotel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Date;

public class BookingDTO {
  private int id;
  private Date dateIn;
  private Date dateOut;
  private String userEmail;
  private double sumPrice;
  private String status;
  private int hotelId;
  private Hotel hotel;
  private String image;

  public BookingDTO() {
  }

  public BookingDTO(int id, Date dateIn, Date dateOut, String userEmail, double sumPrice, String status, int hotelId, Hotel hotel,String image) {
    this.id = id;
    this.dateIn = dateIn;
    this.dateOut = dateOut;
    this.userEmail = userEmail;
    this.sumPrice = sumPrice;
    this.status = status;
    this.hotelId = hotelId;
    this.hotel = hotel;
    this.image = image;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getDateIn() {
    return dateIn;
  }

  public void setDateIn(Date dateIn) {
    this.dateIn = dateIn;
  }

  public Date getDateOut() {
    return dateOut;
  }

  public void setDateOut(Date dateOut) {
    this.dateOut = dateOut;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public double getSumPrice() {
    return sumPrice;
  }

  public void setSumPrice(double sumPrice) {
    this.sumPrice = sumPrice;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getHotelId() {
    return hotelId;
  }

  public void setHotelId(int hotelId) {
    this.hotelId = hotelId;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
