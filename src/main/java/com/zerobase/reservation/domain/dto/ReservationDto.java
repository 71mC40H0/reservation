package com.zerobase.reservation.domain.dto;

import com.zerobase.reservation.domain.model.Reservation;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Long id;
    private Long customerId;
    private Long restaurantId;
    private LocalDateTime visitDateTime;
    private int numberOfPeople;

    public static ReservationDto from(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .customerId(reservation.getCustomerId())
                .restaurantId(reservation.getRestaurantId())
                .visitDateTime(reservation.getVisitDateTime())
                .numberOfPeople(reservation.getNumberOfPeople())
                .build();
    }
}
