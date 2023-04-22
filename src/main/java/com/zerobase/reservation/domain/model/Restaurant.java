package com.zerobase.reservation.domain.model;

import com.zerobase.reservation.domain.form.AddRestaurantForm;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long managerId;
    private String name;
    private String address;
    private String phone;
    private String description;
    private LocalTime openTime;
    private LocalTime lastEntryTime;
    private double longitude;
    private double latitude;
    private double rating;

    public static Restaurant of(Long managerId, AddRestaurantForm form) {
        return Restaurant.builder()
                .managerId(managerId)
                .name(form.getName())
                .address(form.getAddress())
                .phone(form.getPhone())
                .description(form.getDescription())
                .openTime(form.getOpenTime())
                .lastEntryTime(form.getLastEntryTime())
                .longitude(form.getLongitude())
                .latitude(form.getLatitude())
                .rating(0.0)
                .build();
    }
}
