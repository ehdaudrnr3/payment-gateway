package com.ssg.homework.t2021hw.payment.agent;

import com.ssg.homework.t2021hw.entity.Payment;
import com.ssg.homework.t2021hw.payment.request.PaymentRequest;
import com.ssg.homework.t2021hw.payment.response.PaymentResponse;
import com.ssg.homework.t2021hw.payment.server.PaymentServer;

public interface PaymentAgent {
    PaymentServer getServer(String paymentCode, String paymentType);
    PaymentResponse payment(PaymentRequest request);
    PaymentResponse cancel(Payment appprovePayment, PaymentRequest request);
}
