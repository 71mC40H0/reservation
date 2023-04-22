package com.zerobase.reservation.controller;

import com.zerobase.reservation.config.JwtAuthenticationProvider;
import com.zerobase.reservation.domain.dto.RestaurantDto;
import com.zerobase.reservation.domain.form.AddRestaurantForm;
import com.zerobase.reservation.service.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager/restaurant")
@RequiredArgsConstructor
public class ManagerRestaurantController {
    private final RestaurantService restaurantService;
    private final JwtAuthenticationProvider provider;

    @PostMapping
    public ResponseEntity<RestaurantDto> addRestaurant(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                       @RequestBody AddRestaurantForm form) {
        return ResponseEntity.ok(
                RestaurantDto.from(restaurantService.addRestaurant(provider.getUserVo(token).getId(), form)));
    }
}
