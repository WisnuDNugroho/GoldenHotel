package com.hotel.GoldenChariot.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomUpsertDto {
    private String roomNumber;
    private String roomType;
    private Double roomPrice;
    private String roomStatus;
}
