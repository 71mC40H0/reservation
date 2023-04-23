package com.zerobase.reservation.domain.model;

import com.zerobase.reservation.domain.form.ReservationForm;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Long restaurantId;

    private LocalDateTime visitDateTime;

    private int numberOfPeople;

    private String verificationCode;
    private LocalDateTime verifyExpiredAt;
    private boolean approved;
    private boolean finished;

    public static Reservation of(Long customerId, Long restaurantId, ReservationForm form, String verificationCode) {
        return Reservation.builder()
                .customerId(customerId)
                .restaurantId(restaurantId)
                .visitDateTime(form.getVisitDateTime())
                .numberOfPeople(form.getNumberOfPeople())
                .verificationCode(verificationCode)
                .verifyExpiredAt(form.getVisitDateTime().minusMinutes(10))
                .approved(false)
                .finished(false)
                .build();
    }
}
