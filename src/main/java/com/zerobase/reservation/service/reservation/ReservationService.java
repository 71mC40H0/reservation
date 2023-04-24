package com.zerobase.reservation.service.reservation;

import com.zerobase.reservation.domain.form.AddReservationForm;
import com.zerobase.reservation.domain.model.Customer;
import com.zerobase.reservation.domain.model.Reservation;
import com.zerobase.reservation.domain.model.Restaurant;
import com.zerobase.reservation.domain.repository.CustomerRepository;
import com.zerobase.reservation.domain.repository.ReservationRepository;
import com.zerobase.reservation.domain.repository.RestaurantRepository;
import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;


    @Transactional
    public Reservation reserveRestaurant(Long customerId, Long restaurantId, AddReservationForm form) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESTAURANT));
        String verificationCode = getRandomCode();
        return reservationRepository.save(Reservation.of(customer, restaurant, form, verificationCode));
    }

    // 키오스크에 인증할 인증코드를 10자리 랜덤 숫자로 생성
    private String getRandomCode() {
        return RandomStringUtils.random(10, false, true);
    }
}
