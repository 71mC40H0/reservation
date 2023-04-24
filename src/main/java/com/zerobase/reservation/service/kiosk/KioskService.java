package com.zerobase.reservation.service.kiosk;

import com.zerobase.reservation.domain.model.Kiosk;
import com.zerobase.reservation.domain.model.Reservation;
import com.zerobase.reservation.domain.model.Restaurant;
import com.zerobase.reservation.domain.repository.KioskRepository;
import com.zerobase.reservation.domain.repository.ReservationRepository;
import com.zerobase.reservation.domain.repository.RestaurantRepository;
import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KioskService {

    private final KioskRepository kioskRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReservationRepository reservationRepository;

    // 키오스크 등록
    @Transactional
    public Kiosk addKiosk(Long managerId, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESTAURANT));

        if (!managerId.equals(restaurant.getManager().getId())) {
            throw new CustomException(ErrorCode.INVALID_RESTAURANT);
        }

        return kioskRepository.save(Kiosk.of(restaurant));
    }


    // 키오스크에서 예약 확인
    @Transactional
    public String checkReservation(Long kioskId, Long reservationId, String verificationCode) {
        Kiosk kiosk = kioskRepository.findById(kioskId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_KIOSK));

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));

        if (!kiosk.getRestaurant().getId().equals(reservation.getRestaurant().getId())) {
            throw new CustomException(ErrorCode.INVALID_KIOSK);
        }

        if (reservation.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new CustomException(ErrorCode.EXPIRED_RESERVATION);
        }


        // approved가 true이고 finished가 false 여야 인증 가능한 상태
        if (!reservation.isApproved()) {
            if (reservation.isFinished()) {
                throw new CustomException(ErrorCode.ALREADY_DENIED_RESERVATION);
            } else {
                throw new CustomException(ErrorCode.NOT_APPROVED_RESERVATION);
            }
        } else {
            if (reservation.isFinished()) {
                throw new CustomException(ErrorCode.ALREADY_FINISHED_RESERVATION);
            }
        }

        if (verificationCode.equals(reservation.getVerificationCode())) {
            reservation.setFinished(true);
        } else {
            throw new CustomException(ErrorCode.INVALID_VERIFICATION_CODE);
        }

        return "예약 확인이 완료되었습니다.";
    }

}
