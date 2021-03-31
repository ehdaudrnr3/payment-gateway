package com.ssg.homework.t2021hw.repository;

import com.ssg.homework.t2021hw.dto.PaymentDto;

import java.util.List;

public interface PaymentRepositoryCustom {
    List<PaymentDto> findPayments(String mbrId, String succYn, Integer size);
}
