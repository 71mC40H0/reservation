package com.zerobase.reservation.controller;

import com.zerobase.reservation.application.SignUpApplication;
import com.zerobase.reservation.domain.form.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signUp")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpApplication signUpApplication;

    @PostMapping("/manager")
    public ResponseEntity<String> managerSignUp(@RequestBody SignUpForm form) {
        return ResponseEntity.ok(signUpApplication.managerSignUp(form));
    }

    @PutMapping("/manager/verify")
    public ResponseEntity<String> managerVerifyEmail(String email, String code) {
        signUpApplication.managerVerify(email, code);
        return ResponseEntity.ok("파트너 회원 가입이 완료되었습니다.");
    }

    @PostMapping("/customer")
    public ResponseEntity<String> customerSignUp(@RequestBody SignUpForm form) {
        return ResponseEntity.ok(signUpApplication.customerSignUp(form));
    }

    @PutMapping("/customer/verify")
    public ResponseEntity<String> customerVerifyEmail(String email, String code) {
        signUpApplication.customerVerify(email, code);
        return ResponseEntity.ok("회원 가입이 완료되었습니다.");
    }
}
