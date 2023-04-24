package com.zerobase.reservation.domain.dto;

import com.zerobase.reservation.domain.model.Restaurant;
import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<ReviewDto> reviews;

    private List<ReservationDto> reservations;

    public static RestaurantDto from(Restaurant restaurant) {
        List<ReservationDto> reservations = restaurant.getReservations()
                .stream().map(ReservationDto::from).collect(Collectors.toList());

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
                .reservations(reservations)
                .build();
    }

    // 검색 결과시 나올 내용
    public static RestaurantDto searchResultFrom(Restaurant restaurant) {
        List<ReviewDto> reviews = restaurant.getReservations()
                .stream().map(r -> ReviewDto.from(r.getReview())).collect(Collectors.toList());

        return RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .longitude(restaurant.getLongitude())
                .latitude(restaurant.getLatitude())
                .rating(restaurant.getRating())
                .reviews(reviews)
                .build();
    }
}
