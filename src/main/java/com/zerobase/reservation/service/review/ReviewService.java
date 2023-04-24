package com.zerobase.reservation.service.review;

import com.zerobase.reservation.domain.form.WriteReviewForm;
import com.zerobase.reservation.domain.model.Reservation;
import com.zerobase.reservation.domain.model.Restaurant;
import com.zerobase.reservation.domain.model.Review;
import com.zerobase.reservation.domain.repository.ReservationRepository;
import com.zerobase.reservation.domain.repository.RestaurantRepository;
import com.zerobase.reservation.domain.repository.ReviewRepository;
import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;

    // 리뷰 작성 시 식당의 평점 갱신

    // 리뷰 작성
    @Transactional
    public Review writeReview(Long customerId, Long reservationId, WriteReviewForm form) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));

        if (!customerId.equals(reservation.getCustomer().getId())) {
            throw new CustomException(ErrorCode.INVALID_RESERVATION);
        }

        if (!reservation.isApproved()) {
            throw new CustomException(ErrorCode.NOT_APPROVED_RESERVATION);
        } else {
            if (!reservation.isFinished()) {
                throw new CustomException(ErrorCode.NOT_FINISHED_RESERVATION);
            }
        }

        if (reservation.getReview() != null) {
            throw new CustomException(ErrorCode.ALREADY_WRITTEN_REVIEW);
        }

        Review review = Review.of(reservation, form);

        reviewRepository.save(review);
        updateRating(reservation.getRestaurant(), form.getRating());

        return review;
    }

    // 평점 갱신
    @Transactional
    public void updateRating(Restaurant restaurant, int rating) {
        int numOfreviews = (int)restaurant.getReservations().stream()
                        .map(Reservation::getReview).filter(Objects::nonNull).count() + 1;
        int ratings = restaurant.getReservations().stream().filter(r -> r.getReview() != null).mapToInt(r -> r.getReview().getRating()).sum() + rating;
        double averageRating = (double) ratings / numOfreviews;

        restaurant.setRating(averageRating);
        restaurantRepository.save(restaurant);
    }

}
