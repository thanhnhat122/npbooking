package hcmute.edu.booking.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;
    @Column(nullable = false)
    private int hotelId;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int peopleNumber;
    @Column(nullable = false)
    private double discount;
    @Column(nullable = false)
    private int singleBed;
    @Column(nullable = false)
    private int doubleBed;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private int remainRoom;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false,columnDefinition="LONGTEXT")
    private String benefitRoom;
    @Column(nullable = false)
    private String bathRoom;
    @Column(nullable = false)
    private String view;
    @Column(nullable = false,columnDefinition="LONGTEXT")
    private String convenient;
    @Column(nullable = false)
    private boolean smoking;


    public Room() {
    }

    public Room(int hotelId, String size, double price, int peopleNumber, double discount, int singleBed, int doubleBed, int quantity, int remainRoom, String type, String benefitRoom, String bathRoom, String view, String convenient, boolean smoking) {
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
        this.benefitRoom = benefitRoom;
        this.bathRoom = bathRoom;
        this.view = view;
        this.convenient = convenient;
        this.smoking = smoking;
    }
}