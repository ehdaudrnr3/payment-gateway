package com.ssg.homework.t2021hw.repository;

import com.ssg.homework.t2021hw.entity.PaymentMaster;

import java.util.List;

public interface PaymentMasterRepositoryCustom {

    List<PaymentMaster> findListByPmtCodeAndAndPmtType(String pmtCode, String pmtType);
}
