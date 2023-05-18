package hcmute.edu.booking.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class DetailBooking implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private int id;
  @Column(nullable = false)
  private int bookingId;
  @Column(nullable = false)
  private int roomId;
  @Column(nullable = false)
  private int quantity;

  public DetailBooking() {
  }

  public DetailBooking(int bookingId, int roomId, int quantity) {
    this.bookingId = bookingId;
    this.roomId = roomId;
    this.quantity = quantity;
  }
}
