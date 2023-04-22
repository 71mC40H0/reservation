package com.zerobase.reservation.domain.repository;

import com.zerobase.reservation.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepositoryCustom {
    List<Restaurant> searchByName(String name);
}
