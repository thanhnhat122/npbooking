package hcmute.edu.booking.DTO;

import hcmute.edu.booking.model.Booking;
import hcmute.edu.booking.model.Room;

import javax.persistence.Column;

public class DetailBookingDTO {
  private int id;
  private int bookingId;
  private int roomId;
  private int quantity;
  private Room room;
  private Booking booking;

  public DetailBookingDTO() {
  }

  public DetailBookingDTO(int id, int bookingId, int roomId, int quantity, Room room,Booking booking) {
    this.id = id;
    this.bookingId = bookingId;
    this.roomId = roomId;
    this.quantity = quantity;
    this.room = room;
    this.booking = booking;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getBookingId() {
    return bookingId;
  }

  public void setBookingId(int bookingId) {
    this.bookingId = bookingId;
  }

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public Booking getBooking() {
    return booking;
  }
  public void setBooking(Booking booking) {
    this.booking = booking;
  }
}
