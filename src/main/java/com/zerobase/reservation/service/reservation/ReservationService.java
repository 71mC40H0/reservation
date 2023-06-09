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
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;

    // 기간과 매장이용자 id로 예약 조회
    public List<Reservation> findAllByCustomerIdAndVisitDateBetween(Long customerId, LocalDate startDate, LocalDate endDate) {
        return reservationRepository.findAllByCustomerIdAndVisitDateBetween(customerId, startDate, endDate);
    }

    // 기간 및 매장 id로 예약 조회
    public List<Reservation> findAllByRestaurantIdAndVisitDateBetween(Long managerId, Long restaurantId, LocalDate startDate, LocalDate endDate) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESTAURANT));

        if (!managerId.equals(restaurant.getManager().getId())) {
            throw new CustomException(ErrorCode.INVALID_RESTAURANT);
        }
        return reservationRepository.findAllByRestaurantIdAndVisitDateBetween(restaurantId, startDate, endDate);
    }

    // 매장 예약
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
