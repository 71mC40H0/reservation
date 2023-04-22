package com.zerobase.reservation.domain.repository;

import com.zerobase.reservation.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByManagerIdAndId(Long managerId, Long id);
}
