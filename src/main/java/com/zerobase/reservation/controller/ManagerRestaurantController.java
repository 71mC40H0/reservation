package com.zerobase.reservation.controller;

import com.zerobase.reservation.config.JwtAuthenticationProvider;
import com.zerobase.reservation.domain.dto.KioskDto;
import com.zerobase.reservation.domain.dto.RestaurantDto;
import com.zerobase.reservation.domain.form.AddRestaurantForm;
import com.zerobase.reservation.service.kiosk.KioskService;
import com.zerobase.reservation.service.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manager/restaurant")
@RequiredArgsConstructor
public class ManagerRestaurantController {
    private final RestaurantService restaurantService;
    private final KioskService kioskService;
    private final JwtAuthenticationProvider provider;

    // 점장이 소유한 식당 조회
    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getRestaurants(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
        return ResponseEntity.ok(restaurantService.findAllByManagerId(provider.getUserVo(token).getId())
                .stream().map(RestaurantDto::from).collect(Collectors.toList()));
    }

    // 식당 추가
    @PostMapping("/add")
    public ResponseEntity<RestaurantDto> addRestaurant(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                       @RequestBody AddRestaurantForm form) {
        return ResponseEntity.ok(
                RestaurantDto.from(restaurantService.addRestaurant(provider.getUserVo(token).getId(), form)));
    }

    @PostMapping("/kiosk/add")
    public ResponseEntity<KioskDto> addKiosk(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                             @RequestParam Long restaurantId) {

        return ResponseEntity.ok(
                KioskDto.from(kioskService.addKiosk(provider.getUserVo(token).getId(), restaurantId)));
    }
}
