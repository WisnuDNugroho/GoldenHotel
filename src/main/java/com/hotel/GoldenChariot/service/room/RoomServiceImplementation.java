package com.hotel.GoldenChariot.service.room;

import com.hotel.GoldenChariot.dao.ReservationRepository;
import com.hotel.GoldenChariot.dao.RoomRepository;
import com.hotel.GoldenChariot.dto.customer.CustomerDto;
import com.hotel.GoldenChariot.dto.room.RoomDto;
import com.hotel.GoldenChariot.dto.room.RoomUpsertDto;
import com.hotel.GoldenChariot.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImplementation implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<RoomDto> getListRoom(Integer page, String roomNumber) {
        Integer getRowsPage = 3;
        Pageable pagination = PageRequest.of(page-1, getRowsPage);
        return roomRepository.findAll(roomNumber, pagination);
    }

    @Override
    public Long getCountPage(String roomNumber) {
        var rowPerPage = 3;
        double totalData = (double) roomRepository.getCount(roomNumber);
        long totalPage = (long) (Math.ceil(totalData / rowPerPage));
        return totalPage;
    }

    @Override
    public RoomUpsertDto getData(String roomNumber) {
        return roomRepository.getData(roomNumber);
    }

    @Override
    public void save(RoomUpsertDto dto) {
        Room room = new Room(dto.getRoomNumber(), dto.getRoomType(), dto.getRoomPrice(), dto.getRoomStatus());
        roomRepository.save(room);
    }

    @Override
    public Boolean checkDependency(String roomNumber) {
        if (reservationRepository.checkDependencyRoom(roomNumber) > 0){
            return false;
        } else {
            roomRepository.deleteById(roomNumber);
            return true;
        }
    }
}
