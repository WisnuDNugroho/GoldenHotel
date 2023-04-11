package com.hotel.GoldenChariot.dao;

import com.hotel.GoldenChariot.dto.room.RoomDto;
import com.hotel.GoldenChariot.dto.room.RoomUpsertDto;
import com.hotel.GoldenChariot.entity.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, String> {

    @Query("""
            SELECT COUNT(r.roomNumber)
            FROM Room AS r
            WHERE r.roomNumber LIKE %:roomNumber%
            """)
    public Long getCount(@Param("roomNumber") String roomNumber);

    @Query("""
            SELECT new com.hotel.GoldenChariot.dto.room.RoomDto(
                r.roomNumber, r.type, r.price, r.status
            )
            FROM Room AS r
            WHERE r.roomNumber LIKE %:roomNumber%
            """)
    public List<RoomDto> findAll(@Param("roomNumber") String roomNumber, Pageable pageable);

    @Query("""
            SELECT new com.hotel.GoldenChariot.dto.room.RoomUpsertDto(
                r.roomNumber, r.type, r.price, r.status
            )
            FROM Room AS r
            WHERE r.roomNumber = :roomNumber
            """)
    public RoomUpsertDto getData(@Param("roomNumber") String roomNumber);
}
