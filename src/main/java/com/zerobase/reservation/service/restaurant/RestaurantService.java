package com.zerobase.reservation.service.restaurant;

import com.zerobase.reservation.domain.form.AddRestaurantForm;
import com.zerobase.reservation.domain.model.Restaurant;
import com.zerobase.reservation.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant addRestaurant(Long managerId, AddRestaurantForm form) {
        return restaurantRepository.save(Restaurant.of(managerId, form));
    }
}
