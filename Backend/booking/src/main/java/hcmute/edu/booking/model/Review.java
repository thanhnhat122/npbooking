package hcmute.edu.booking.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;
    @Column(nullable = false)
    private int hotelId;
    @Column(nullable = false)
    private String userEmail;
    @Column(nullable = false,columnDefinition="LONGTEXT")
    private String comment;
    @Column(nullable = false)
    private int rate;

    public Review() {
    }

    public Review(int hotelId, String userEmail, String comment, int rate) {
        this.hotelId = hotelId;
        this.userEmail = userEmail;
        this.comment = comment;
        this.rate = rate;
    }
}