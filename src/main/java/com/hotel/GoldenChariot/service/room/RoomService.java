package com.hotel.GoldenChariot.service.room;

import com.hotel.GoldenChariot.dto.customer.CustomerDto;
import com.hotel.GoldenChariot.dto.room.RoomDto;
import com.hotel.GoldenChariot.dto.room.RoomUpsertDto;

import java.util.List;

public interface RoomService {
    public List<RoomDto> getListRoom(Integer page, String roomNumber);
    public Long getCountPage(String roomNumber);
    public RoomUpsertDto getData(String roomNumber);
    public void save(RoomUpsertDto dto);
    public Boolean checkDependency(String roomNumber);
}
