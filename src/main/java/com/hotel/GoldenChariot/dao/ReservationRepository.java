package com.hotel.GoldenChariot.dao;

import com.hotel.GoldenChariot.dto.reservation.DetailReservationDto;
import com.hotel.GoldenChariot.entity.Reservation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
            SELECT COUNT(trans.transactionID)
            FROM Reservation AS trans
            WHERE trans.customerUsername LIKE %:username%
            """)
    public Long getCountTransaction(@Param("username") String username);

    @Query("""
            SELECT new com.hotel.GoldenChariot.dto.reservation.DetailReservationDto(
                trans.transactionID,
                trans.checkIn,
                trans.checkOut,
                trans.roomNumber,
                room.price
            )
            FROM Reservation AS trans
            JOIN trans.room AS room
            JOIN trans.customer AS cust
            WHERE trans.customerUsername LIKE %:username%
            """)
    public List<DetailReservationDto> findTransaction(@Param("username") String username, Pageable pageable);

    @Query("""
            SELECT COUNT(trans.transactionID)
            FROM Reservation AS trans
            WHERE trans.customerUsername LIKE %:username%
            """)
    public Long checkDependencyCustomer(@Param("username") String username);

    @Query("""
            SELECT COUNT(trans.transactionID)
            FROM Reservation AS trans
            WHERE trans.roomNumber LIKE %:roomNumber%
            """)
    public Long checkDependencyRoom(@Param("roomNumber") String roomNumber);
}
