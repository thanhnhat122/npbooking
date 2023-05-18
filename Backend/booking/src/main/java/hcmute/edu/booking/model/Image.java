package hcmute.edu.booking.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;
    @Column(nullable = true)
    private int hotelId;
    @Column(nullable = true)
    private int roomId;
    @Column(nullable = false)
    private String link;

    public Image() {
    }

    public Image(int hotelId, int roomId, String link) {
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.link = link;
    }
}