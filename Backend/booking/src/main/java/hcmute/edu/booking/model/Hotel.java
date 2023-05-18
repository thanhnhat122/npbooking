package hcmute.edu.booking.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Hotel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,columnDefinition="LONGTEXT")
    private String description;
    @Column(nullable = false)
    private int province;
    @Column(nullable = false)
    private String address;
    private String benefit;
    private int star;
    private boolean breakfast;
    private double distanceCenter;
    private boolean sustainTour;
    private boolean sea;
    private String owner;

    public Hotel() {
    }

    public Hotel(String name, String description, int province, String address, String benefit, int star, boolean breakfast, double distanceCenter, boolean sustainTour, boolean sea,String owner) {
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
        this.owner = owner;
    }
}