package com.zerobase.reservation.service.manager;

import com.zerobase.reservation.domain.model.Manager;
import com.zerobase.reservation.domain.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;

    public Optional<Manager> findByIdAndEmail(Long id, String email) {
        return managerRepository.findByIdAndEmail(id, email);
    }

    public Optional<Manager> findPartnerMember(String email, String password) {
        return managerRepository.findByEmailAndPasswordAndPartnerIsTrue(email, password);
    }
}
