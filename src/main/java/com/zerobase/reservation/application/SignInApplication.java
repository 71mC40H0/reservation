package com.zerobase.reservation.application;

import com.zerobase.reservation.config.JwtAuthenticationProvider;
import com.zerobase.reservation.domain.common.UserType;
import com.zerobase.reservation.domain.form.SignInForm;
import com.zerobase.reservation.domain.model.Manager;
import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import com.zerobase.reservation.service.manager.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final ManagerService managerService;
    private final JwtAuthenticationProvider provider;

    public String managerLoginToken(SignInForm form) {
        Manager m = managerService.findPartnerMember(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));

        return provider.createToken(m.getEmail(), m.getId(), UserType.MANAGER);
    }


}
