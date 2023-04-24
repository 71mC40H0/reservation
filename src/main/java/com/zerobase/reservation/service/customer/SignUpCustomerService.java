package com.zerobase.reservation.service.customer;

import com.zerobase.reservation.domain.form.SignUpForm;
import com.zerobase.reservation.domain.model.Customer;
import com.zerobase.reservation.domain.repository.CustomerRepository;
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
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;

    // 회원 가입
    public Customer signUp(SignUpForm form) {
        return customerRepository.save(Customer.from(form));
    }

    public boolean isEmailExist(String email) {
        return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT)).isPresent();
    }

    // 이메일 인증
    @Transactional
    public void verifyEmail(String email, String code) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        if (customer.isVerify()) {
            throw new CustomException(ErrorCode.ALREADY_VERIFIED);
        } else if (!customer.getVerificationCode().equals(code)) {
            throw new CustomException(ErrorCode.WRONG_VERIFICATION);
        } else if (customer.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new CustomException(ErrorCode.EXPIRE_CODE);
        }
        customer.setVerify(true);
    }

    // 이메일 인증 기간 및 인증 코드 설정
    @Transactional
    public void changeCustomerValidateEmail(Long customerId, String verificationCode) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setVerificationCode(verificationCode);
            customer.setVerifyExpiredAt(LocalDateTime.now().plusDays(1));
        } else {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }
    }
}
