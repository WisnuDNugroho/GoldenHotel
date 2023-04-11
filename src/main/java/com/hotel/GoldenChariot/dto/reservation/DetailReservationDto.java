package com.hotel.GoldenChariot.dto.reservation;

import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetailReservationDto {
    private Long transactionID;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String roomNumber;
    private Double cost;

    public DetailReservationDto(Long transactionID, LocalDate checkIn, LocalDate checkOut, String roomNumber, Double price){
        this.transactionID = transactionID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomNumber = roomNumber;
        Long duration = ChronoUnit.DAYS.between(checkIn, checkOut);
        this.cost = duration * price;
    }
}
