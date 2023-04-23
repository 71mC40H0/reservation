package com.zerobase.reservation.controller;

import com.zerobase.reservation.config.JwtAuthenticationProvider;
import com.zerobase.reservation.domain.dto.ReservationDto;
import com.zerobase.reservation.domain.form.ReservationForm;
import com.zerobase.reservation.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final ReservationService reservationService;
    private final JwtAuthenticationProvider provider;

    @PostMapping("/reservation")
    public ResponseEntity<ReservationDto> reserveRestaurant(
            @RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestParam Long restaurantId, @RequestBody ReservationForm form) {

        return ResponseEntity.ok(ReservationDto.from(
                reservationService.reserveRestaurant(
                        provider.getUserVo(token).getId(), restaurantId, form)));
    }

}
