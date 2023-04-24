package com.zerobase.reservation.service.restaurant;

import com.zerobase.reservation.domain.form.AddRestaurantForm;
import com.zerobase.reservation.domain.model.Manager;
import com.zerobase.reservation.domain.model.Restaurant;
import com.zerobase.reservation.domain.repository.ManagerRepository;
import com.zerobase.reservation.domain.repository.RestaurantRepository;
import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final ManagerRepository managerRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> findAllByManagerId(Long managerId) {
        return restaurantRepository.findAllByManagerId(managerId);
    }

    // 매장 추가
    @Transactional
    public Restaurant addRestaurant(Long managerId, AddRestaurantForm form) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        return restaurantRepository.save(Restaurant.of(manager, form));
    }
}
