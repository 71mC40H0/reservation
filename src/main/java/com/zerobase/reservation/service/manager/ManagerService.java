package com.zerobase.reservation.service.manager;

import com.zerobase.reservation.domain.model.Manager;
import com.zerobase.reservation.domain.model.Reservation;
import com.zerobase.reservation.domain.repository.ManagerRepository;
import com.zerobase.reservation.domain.repository.ReservationRepository;
import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final ReservationRepository reservationRepository;

    public Optional<Manager> findByIdAndEmail(Long id, String email) {
        return managerRepository.findByIdAndEmail(id, email);
    }

    public Optional<Manager> findPartnerMember(String email, String password) {
        return managerRepository.findByEmailAndPasswordAndPartnerIsTrue(email, password);
    }

    // 예약을 승인한 경우 Reservation의 approved가 true가 되며,
    // 예약을 거절하거나, 예약 시간 안에 방문확인한 경우 Reservation의 finished가 true가 된다

    // 예약 승인
    @Transactional
    public void approveReservation(Long managerId, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));

        if (!managerId.equals(reservation.getRestaurant().getId())) {
            throw new CustomException(ErrorCode.INVALID_RESTAURANT);
        }

        if (reservation.isApproved()) {
            throw new CustomException(ErrorCode.ALREADY_APPROVED_RESERVATION);
        } else if (reservation.isFinished()) {
            throw new CustomException(ErrorCode.ALREADY_DENIED_RESERVATION);
        }

        reservation.setApproved(true);
    }

    // 예약 거절
    @Transactional
    public void rejectReservation(Long managerId, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));

        if (!managerId.equals(reservation.getRestaurant().getId())) {
            throw new CustomException(ErrorCode.INVALID_RESTAURANT);
        }

        if (reservation.isApproved()) {
            throw new CustomException(ErrorCode.ALREADY_APPROVED_RESERVATION);
        } else if (reservation.isFinished()) {
            throw new CustomException(ErrorCode.ALREADY_DENIED_RESERVATION);
        }

        reservation.setFinished(true);
    }
}
