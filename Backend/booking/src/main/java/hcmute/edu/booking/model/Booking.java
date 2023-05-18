package hcmute.edu.booking.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;
    @Column(nullable = false)
    private Date dateIn;
    @Column(nullable = false)
    private Date dateOut;
    @Column(nullable = false)
    private String userEmail;
    @Column(nullable = false)
    private double sumPrice;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private int hotelId;

    public Booking() {
    }

    public Booking(Date dateIn, Date dateOut, String userEmail, double sumPrice, String status,int hotelId) {
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.userEmail = userEmail;
        this.sumPrice = sumPrice;
        this.status = status;
        this.hotelId = hotelId;
    }
}