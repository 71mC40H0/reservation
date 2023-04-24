package com.zerobase.reservation.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckReservationForm {
    private Long reservationId;
    private String verificationCode;

}
