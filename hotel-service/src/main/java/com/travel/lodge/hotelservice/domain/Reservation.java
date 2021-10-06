package com.travel.lodge.hotelservice.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date checkInDateTime;

    private Date checkOutDateTime;

    private Long guestId;

    @CreationTimestamp
    private Date modifiedDate;

    private String status;

    @Column(unique = true)
    private String referenceNo;

    @OneToOne
    @JoinColumn(name = "roomId", nullable = false, referencedColumnName = "id")
    private Room roomId;
}
