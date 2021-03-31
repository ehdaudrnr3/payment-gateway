package com.ssg.homework.t2021hw.service;

import com.ssg.homework.t2021hw.entity.PaymentMaster;
import com.ssg.homework.t2021hw.repository.PaymentMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMasterService {

    private final PaymentMasterRepository paymentMasterRepository;

    public List<PaymentMaster> getPaymentMasters(String pmtCode, String pmtType) {
        return paymentMasterRepository.findListByPmtCodeAndAndPmtType(pmtCode, pmtType);
    }

    public PaymentMaster getPaymentMaster(String pmtCode, String pmtType) {
        return paymentMasterRepository.findByPmtCodeAndAndPmtType(pmtCode, pmtType);
    }
}
