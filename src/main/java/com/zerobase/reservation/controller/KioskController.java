package com.zerobase.reservation.controller;

import com.zerobase.reservation.domain.form.CheckReservationForm;
import com.zerobase.reservation.service.kiosk.KioskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kiosk")
public class KioskController {
    private final KioskService kioskService;

    @PostMapping("/check")
    public ResponseEntity<String> checkReservation(@RequestParam Long kioskId, @RequestBody CheckReservationForm form) {
        return ResponseEntity.ok(kioskService.checkReservation(kioskId, form.getReservationId(), form.getVerificationCode()));
    }
}
