package com.zerobase.reservation.controller;

import com.zerobase.reservation.config.JwtAuthenticationProvider;
import com.zerobase.reservation.domain.dto.ReservationDto;
import com.zerobase.reservation.domain.dto.ReviewDto;
import com.zerobase.reservation.domain.form.AddReservationForm;
import com.zerobase.reservation.domain.form.WriteReviewForm;
import com.zerobase.reservation.service.reservation.ReservationService;
import com.zerobase.reservation.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final ReservationService reservationService;
    private final ReviewService reviewService;
    private final JwtAuthenticationProvider provider;

    // 레스토랑 예약
    @PostMapping("/reservation/reserve")
    public ResponseEntity<ReservationDto> reserveRestaurant(
            @RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestParam Long restaurantId, @RequestBody AddReservationForm form) {

        return ResponseEntity.ok(ReservationDto.from(
                reservationService.reserveRestaurant(
                        provider.getUserVo(token).getId(), restaurantId, form)));
    }

    // 예약 조회
    @GetMapping("/reservation")
    public ResponseEntity<List<ReservationDto>> getReservations(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(reservationService.findAllByCustomerIdAndVisitDateBetween(
                provider.getUserVo(token).getId(), startDate, endDate)
                .stream().map(ReservationDto::from).collect(Collectors.toList()));
    }

    // 리뷰 작성
    @PostMapping("/review/write")
    public ResponseEntity<ReviewDto> writeReview(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                 @RequestParam Long reservationId,
                                                 @RequestBody WriteReviewForm form) {

        return ResponseEntity.ok(ReviewDto.from(reviewService.writeReview(
                provider.getUserVo(token).getId(), reservationId, form)));
    }

}
