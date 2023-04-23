package com.zerobase.reservation.service.reservation;

import com.zerobase.reservation.domain.form.ReservationForm;
import com.zerobase.reservation.domain.model.Reservation;
import com.zerobase.reservation.domain.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Transactional
    public Reservation reserveRestaurant(Long customerId, Long restaurantId, ReservationForm form) {
        String verificationCode = getRandomCode();
        return reservationRepository.save(Reservation.of(customerId, restaurantId, form, verificationCode));
    }

    // 키오스크에 인증할 인증코드를 10자리 랜덤 숫자로 생성
    private String getRandomCode() {
        return RandomStringUtils.random(10, false, true);
    }
}
