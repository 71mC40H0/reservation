package com.zerobase.reservation.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddReservationForm {

    private LocalDate visitDate;
    private LocalTime visitTime;
    int numberOfPeople;

}
