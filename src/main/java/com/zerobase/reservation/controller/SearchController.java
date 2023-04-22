package com.zerobase.reservation.controller;

import com.zerobase.reservation.domain.dto.RestaurantDto;
import com.zerobase.reservation.service.restaurant.RestaurantSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("search/restaurant")
public class SearchController {
    private final RestaurantSearchService restaurantSearchService;

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(
                restaurantSearchService.searchByName(name).stream()
                        .map(RestaurantDto::searchResultFrom).collect(Collectors.toList()));
    }

    @GetMapping("/detail")
    public ResponseEntity<RestaurantDto> getDetail(@RequestParam Long restaurantId) {
        return ResponseEntity.ok(RestaurantDto.from(restaurantSearchService.getByRestaurantId(restaurantId)));
    }

}
