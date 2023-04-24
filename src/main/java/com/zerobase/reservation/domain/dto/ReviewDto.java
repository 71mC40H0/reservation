package com.zerobase.reservation.domain.dto;

import com.zerobase.reservation.domain.model.Review;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private Long reservationId;
    private String restaurantName;
    private String text;
    private LocalDate writeDate;
    private int rating;

    public static ReviewDto from(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .reservationId(review.getReservation().getId())
                .restaurantName(review.getReservation().getRestaurant().getName())
                .text(review.getText())
                .writeDate(review.getWriteDate())
                .rating(review.getRating())
                .build();
    }
}
