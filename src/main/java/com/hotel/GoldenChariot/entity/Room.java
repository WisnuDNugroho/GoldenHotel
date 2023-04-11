package com.hotel.GoldenChariot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Room")
public class Room {

    @Id
    @Column(name = "RoomNumber")
    private String roomNumber;

    @Column(name = "Type")
    private String type;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Status")
    private String status;
}
