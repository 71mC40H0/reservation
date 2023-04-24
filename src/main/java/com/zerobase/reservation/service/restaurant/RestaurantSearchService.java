package com.zerobase.reservation.service.restaurant;

import com.zerobase.reservation.domain.model.Restaurant;
import com.zerobase.reservation.domain.repository.RestaurantRepository;
import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantSearchService {
    private final RestaurantRepository restaurantRepository;

    // 매장 검색
    public List<Restaurant> searchByName(String name) {
        return restaurantRepository.searchByName(name);
    }

    public Restaurant getByRestaurantId(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESTAURANT));
    }
}
