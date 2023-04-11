package com.hotel.GoldenChariot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionId")
    private Long transactionID;

    @Column(name = "CheckIn")
    private LocalDate checkIn;

    @Column(name = "CheckOut")
    private LocalDate checkOut;

    @Column(name = "RoomNumber")
    private String roomNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RoomNumber", insertable = false, updatable = false)
    private Room room;

    @Column(name = "CustomerUsername")
    private String customerUsername;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CustomerUsername", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "Status")
    private String status;

    @Column(name = "Price")
    private Double price;

    public Reservation(Long transactionID, LocalDate checkIn, LocalDate checkOut, String roomNumber, String customerUsername, String status, Double price){
        this.transactionID = transactionID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomNumber = roomNumber;
        this.customerUsername = customerUsername;
        this.status = status;
        this.price = price;
    }
}
