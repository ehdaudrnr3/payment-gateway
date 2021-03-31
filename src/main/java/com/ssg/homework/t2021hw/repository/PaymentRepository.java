package com.ssg.homework.t2021hw.repository;

import com.ssg.homework.t2021hw.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String>, PaymentRepositoryCustom  {
}
