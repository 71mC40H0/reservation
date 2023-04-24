package com.zerobase.reservation.controller;

import com.zerobase.reservation.config.JwtAuthenticationProvider;
import com.zerobase.reservation.domain.dto.ReservationDto;
import com.zerobase.reservation.service.manager.ManagerService;
import com.zerobase.reservation.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manager/reservation")
@RequiredArgsConstructor
public class ManagerReservationController {
    private final ManagerService managerService;
    private final ReservationService reservationService;
    private final JwtAuthenticationProvider provider;

    // 날짜로 해당 레스토랑의 예약 조회
    @GetMapping
    public ResponseEntity<List<ReservationDto>> getReservations(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                                    @RequestParam Long restaurantId,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(reservationService.findAllByRestaurantIdAndVisitDateBetween(
                        provider.getUserVo(token).getId(), restaurantId, startDate, endDate)
                .stream().map(ReservationDto::from).collect(Collectors.toList()));
    }

    @PutMapping("/approve")
    public ResponseEntity<String> approveReservation(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                     @RequestParam Long reservationId) {
        managerService.approveReservation(provider.getUserVo(token).getId(), reservationId);
        return ResponseEntity.ok("예약이 승인되었습니다.");
    }

    @PutMapping("/reject")
    public ResponseEntity<String> rejectReservation(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                    @RequestParam Long reservationId) {
        managerService.rejectReservation(provider.getUserVo(token).getId(), reservationId);
        return ResponseEntity.ok("예약이 거절되었습니다.");
    }
}
