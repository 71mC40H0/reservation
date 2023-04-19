package com.zerobase.reservation.service.manager;

import com.zerobase.reservation.domain.form.SignUpForm;
import com.zerobase.reservation.domain.model.Manager;
import com.zerobase.reservation.domain.repository.ManagerRepository;
import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpManagerService {

    private final ManagerRepository managerRepository;

    public Manager signUp(SignUpForm form) {
        return managerRepository.save(Manager.from(form));
    }

    public boolean isEmailExist(String email) {
        return managerRepository.findByEmail(email.toLowerCase(Locale.ROOT)).isPresent();
    }

    // 점장은 이메일 인증 시 파트너 회원이 되는 것으로 설정
    @Transactional
    public void verifyEmail(String email, String code) {
        Manager manager = managerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        if(manager.isPartner()) {
            throw new CustomException(ErrorCode.ALREADY_VERIFIED);
        } else if(!manager.getVerificationCode().equals(code)) {
            throw new CustomException(ErrorCode.WRONG_VERIFICATION);
        } else if(manager.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new CustomException(ErrorCode.EXPIRE_CODE);
        }
        manager.setPartner(true);
    }

    @Transactional
    public void changeManagerValidateEmail(Long managerId, String verificationCode) {
        Optional<Manager> managerOptional = managerRepository.findById(managerId);

        if(managerOptional.isPresent()) {
            Manager manager = managerOptional.get();
            manager.setVerificationCode(verificationCode);
            manager.setVerifyExpiredAt(LocalDateTime.now().plusDays(1));
        } else {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }
    }
}
