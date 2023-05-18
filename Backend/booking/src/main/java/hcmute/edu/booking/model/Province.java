package hcmute.edu.booking.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Province implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private int id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String image;
  @Column(nullable = false)
  private int placeNumber;

  public Province(String name, String image, int placeNumber) {
    this.name = name;
    this.image = image;
    this.placeNumber = placeNumber;
  }

  public Province() {
  }
}
