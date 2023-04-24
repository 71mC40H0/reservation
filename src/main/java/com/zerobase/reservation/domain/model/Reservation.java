package com.zerobase.reservation.domain.model;

import com.zerobase.reservation.domain.form.AddReservationForm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private LocalDate visitDate;
    private LocalTime visitTime;

    private int numberOfPeople;

    private String verificationCode;
    private LocalDateTime verifyExpiredAt;
    private boolean approved;
    private boolean finished;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Review review;

    public static Reservation of(Customer customer, Restaurant restaurant, AddReservationForm form, String verificationCode) {
        return Reservation.builder()
                .customer(customer)
                .restaurant(restaurant)
                .visitDate(form.getVisitDate())
                .visitTime(form.getVisitTime())
                .numberOfPeople(form.getNumberOfPeople())
                .verificationCode(verificationCode)
                .verifyExpiredAt(form.getVisitDate().atTime(form.getVisitTime()).minusMinutes(10))
                .approved(false)
                .finished(false)
                .build();
    }
}
