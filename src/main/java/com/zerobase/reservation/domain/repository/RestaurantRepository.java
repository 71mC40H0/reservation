package com.zerobase.reservation.domain.repository;

import com.zerobase.reservation.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom {
    List<Restaurant> findAllByManagerId(Long managerId);
}
