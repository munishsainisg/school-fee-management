package com.school.management.studentfees.repository;

import com.school.management.studentfees.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeeRepository extends JpaRepository<Fee, Long> {

    Optional<Fee> findByReceiptNo(String receiptNo);
}
