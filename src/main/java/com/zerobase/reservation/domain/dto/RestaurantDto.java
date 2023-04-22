package com.zerobase.reservation.domain.dto;

import com.zerobase.reservation.domain.model.Restaurant;
import lombok.*;

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
    private LocalTime openTime;
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
