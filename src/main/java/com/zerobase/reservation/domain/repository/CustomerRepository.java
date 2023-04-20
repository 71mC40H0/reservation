package com.zerobase.reservation.domain.repository;

import com.zerobase.reservation.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByIdAndEmail(Long id, String email);

    Optional<Customer> findByEmailAndPasswordAndVerifyIsTrue(String email, String password);


}
