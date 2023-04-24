package com.zerobase.reservation.domain.model;

import com.zerobase.reservation.domain.form.AddRestaurantForm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    private String name;
    private String address;
    private String phone;
    private String description;
    private LocalTime openTime;
    private LocalTime lastEntryTime;
    private double longitude;
    private double latitude;
    private double rating;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kiosk> kiosks = new ArrayList<>();

    public static Restaurant of(Manager manager, AddRestaurantForm form) {
        return Restaurant.builder()
                .manager(manager)
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
