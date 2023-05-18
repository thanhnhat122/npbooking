package hcmute.edu.booking.DTO;

import javax.persistence.Column;

public class RoomDTO {
  private int id;
  private int hotelId;
  private String size;
  private double price;
  private int peopleNumber;
  private double discount;
  private int singleBed;
  private int doubleBed;
  private int quantity;
  private int remainRoom;
  private String type;
  private int numNight;
  private String benefitRoom;
  private String bathRoom;
  private String view;
  private String convenient;
  private boolean smoking;

  public RoomDTO() {
  }

  public RoomDTO(int id, int hotelId, String size, double price, int peopleNumber, double discount, int singleBed, int doubleBed, int quantity, int remainRoom, String type, int numNight, String benefitRoom, String bathRoom, String view, String convenient, boolean smoking) {
    this.id = id;
    this.hotelId = hotelId;
    this.size = size;
    this.price = price;
    this.peopleNumber = peopleNumber;
    this.discount = discount;
    this.singleBed = singleBed;
    this.doubleBed = doubleBed;
    this.quantity = quantity;
    this.remainRoom = remainRoom;
    this.type = type;
    this.numNight = numNight;
    this.benefitRoom = benefitRoom;
    this.bathRoom = bathRoom;
    this.view = view;
    this.convenient = convenient;
    this.smoking = smoking;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getHotelId() {
    return hotelId;
  }

  public void setHotelId(int hotelId) {
    this.hotelId = hotelId;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getPeopleNumber() {
    return peopleNumber;
  }

  public void setPeopleNumber(int peopleNumber) {
    this.peopleNumber = peopleNumber;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

  public int getSingleBed() {
    return singleBed;
  }

  public void setSingleBed(int singleBed) {
    this.singleBed = singleBed;
  }

  public int getDoubleBed() {
    return doubleBed;
  }

  public void setDoubleBed(int doubleBed) {
    this.doubleBed = doubleBed;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getRemainRoom() {
    return remainRoom;
  }

  public void setRemainRoom(int remainRoom) {
    this.remainRoom = remainRoom;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getNumNight() {
    return numNight;
  }

  public void setNumNight(int numNight) {
    this.numNight = numNight;
  }

  public String getBenefitRoom() {
    return benefitRoom;
  }

  public void setBenefitRoom(String benefitRoom) {
    this.benefitRoom = benefitRoom;
  }

  public String getBathRoom() {
    return bathRoom;
  }

  public void setBathRoom(String bathRoom) {
    this.bathRoom = bathRoom;
  }

  public String getView() {
    return view;
  }

  public void setView(String view) {
    this.view = view;
  }

  public String getConvenient() {
    return convenient;
  }

  public void setConvenient(String convenient) {
    this.convenient = convenient;
  }

  public boolean isSmoking() {
    return smoking;
  }

  public void setSmoking(boolean smoking) {
    this.smoking = smoking;
  }
}
