package hcmute.edu.booking.DTO;

import hcmute.edu.booking.model.Room;

import java.util.Optional;

public class HotelDTO {
  private int id;
  private String name;
  private String description;
  private int province;
  private String address;
  private String benefit;
  private int star;
  private boolean breakfast;
  private double distanceCenter;
  private boolean sustainTour;
  private boolean sea;
  private Room room;
  private double score;
  private int numReview;
  private String image;

  private int numNight;

  public HotelDTO() {
  }

  public HotelDTO(int id, String name, String description, int province, String address, String benefit, int star, boolean breakfast, double distanceCenter, boolean sustainTour, boolean sea, Room room, double score, int numReview, String image,int numNight) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.province = province;
    this.address = address;
    this.benefit = benefit;
    this.star = star;
    this.breakfast = breakfast;
    this.distanceCenter = distanceCenter;
    this.sustainTour = sustainTour;
    this.sea = sea;
    this.room = room;
    this.score = score;
    this.numReview = numReview;
    this.image = image;
    this.numNight = numNight;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getProvince() {
    return province;
  }

  public void setProvince(int province) {
    this.province = province;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getBenefit() {
    return benefit;
  }

  public void setBenefit(String benefit) {
    this.benefit = benefit;
  }

  public int getStar() {
    return star;
  }

  public void setStar(int star) {
    this.star = star;
  }

  public boolean isBreakfast() {
    return breakfast;
  }

  public void setBreakfast(boolean breakfast) {
    this.breakfast = breakfast;
  }

  public double getDistanceCenter() {
    return distanceCenter;
  }

  public void setDistanceCenter(double distanceCenter) {
    this.distanceCenter = distanceCenter;
  }

  public boolean isSustainTour() {
    return sustainTour;
  }

  public void setSustainTour(boolean sustainTour) {
    this.sustainTour = sustainTour;
  }

  public boolean isSea() {
    return sea;
  }

  public void setSea(boolean sea) {
    this.sea = sea;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

  public int getNumReview() {
    return numReview;
  }

  public void setNumReview(int numReview) {
    this.numReview = numReview;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public int getNumNight() {
    return numNight;
  }

  public void setNumNight(int numNight) {
    this.numNight = numNight;
  }
}
