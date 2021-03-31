package com.ssg.homework.t2021hw.repository;

import com.ssg.homework.t2021hw.entity.PaymentMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMasterRepository extends JpaRepository<PaymentMaster, String>, PaymentMasterRepositoryCustom {
    PaymentMaster findByPmtCodeAndAndPmtType(String pmtCode, String pmtType);
}
