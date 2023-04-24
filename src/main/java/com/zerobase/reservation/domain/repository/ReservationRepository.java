package com.zerobase.reservation.domain.repository;

import com.zerobase.reservation.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByRestaurantIdAndVisitDateBetween(Long restaurantId, LocalDate startDate, LocalDate endDate);
}
