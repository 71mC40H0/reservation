package com.zerobase.reservation.domain.dto;

import com.zerobase.reservation.domain.model.Restaurant;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String description;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime lastEntryTime;
    private double longitude;
    private double latitude;
    private double rating;

    public static RestaurantDto from(Restaurant restaurant) {
        return RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phone(restaurant.getPhone())
                .description(restaurant.getDescription())
                .openTime(restaurant.getOpenTime())
                .lastEntryTime(restaurant.getLastEntryTime())
                .longitude(restaurant.getLongitude())
                .latitude(restaurant.getLatitude())
                .rating(restaurant.getRating())
                .build();
    }

    // 검색 결과시 나올 내용
    public static RestaurantDto searchResultFrom(Restaurant restaurant) {
        return RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .longitude(restaurant.getLongitude())
                .latitude(restaurant.getLatitude())
                .rating(restaurant.getRating())
                .build();
    }
}
