package com.zerobase.reservation.domain.repository;

import com.zerobase.reservation.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
