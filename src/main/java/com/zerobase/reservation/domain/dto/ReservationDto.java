package com.zerobase.reservation.domain.dto;

import com.zerobase.reservation.domain.model.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Long id;
    private Long customerId;
    private String customerName;
    private Long restaurantId;
    private LocalDate visitDate;
    private LocalTime visitTime;
    private int numberOfPeople;
    private String verificationCode;

    private boolean approved;
    private boolean finished;

    public static ReservationDto from(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .customerId(reservation.getCustomer().getId())
                .customerName(reservation.getCustomer().getName())
                .restaurantId(reservation.getRestaurant().getId())
                .visitDate(reservation.getVisitDate())
                .visitTime(reservation.getVisitTime())
                .numberOfPeople(reservation.getNumberOfPeople())
                .verificationCode(reservation.getVerificationCode())
                .approved(reservation.isApproved())
                .finished(reservation.isFinished())
                .build();
    }
}
