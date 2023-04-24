package com.zerobase.reservation.domain.model;

import com.zerobase.reservation.domain.form.WriteReviewForm;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Reservation reservation;

    private String text;
    private LocalDate writeDate;
    @Min(0)
    @Max(5)
    private int rating;

    public static Review of(Reservation reservation, WriteReviewForm form) {
        return Review.builder()
                .reservation(reservation)
                .text(form.getText())
                .writeDate(LocalDate.now())
                .rating(form.getRating())
                .build();
    }


}
