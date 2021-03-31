package com.ssg.homework.t2021hw.payment.server;

public interface PaymentServerContainer {
    PaymentServer getActiveServer(String paymentCode, String paymentType);
}
