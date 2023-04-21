package com.zerobase.reservation.controller;

import com.zerobase.reservation.application.SignInApplication;
import com.zerobase.reservation.domain.form.SignInForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/signIn")
@RequiredArgsConstructor
public class SignInController {
    private final SignInApplication signInApplication;

    @PostMapping("/manager")
    public ResponseEntity<String> loginManager(@RequestBody SignInForm form) {
        return ResponseEntity.ok(signInApplication.managerLoginToken(form));
    }

    @PostMapping("/customer")
    public ResponseEntity<String> loginCustomer(@RequestBody SignInForm form) {
        return ResponseEntity.ok(signInApplication.customerLoginToken(form));
    }
}
