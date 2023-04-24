package com.zerobase.reservation.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddRestaurantForm {

    private String name;
    private String address;
    private String phone;
    private String description;
    private LocalTime openTime;
    private LocalTime lastEntryTime;
    private double longitude;
    private double latitude;

}
