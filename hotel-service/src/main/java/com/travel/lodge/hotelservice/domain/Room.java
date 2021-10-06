package com.travel.lodge.hotelservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @Column
    private long guestId;

    private double price;

    @Column(nullable = true)
    private Date checkInDateTime;

    @Column(nullable = true)
    private Date checkOutDateTime;

    @Column(nullable = false)
    private String bookedStatus;

    @ManyToOne
    @JoinColumn(name = "hotelId", nullable = false, referencedColumnName = "id")
    private Hotel hotelId;


}
