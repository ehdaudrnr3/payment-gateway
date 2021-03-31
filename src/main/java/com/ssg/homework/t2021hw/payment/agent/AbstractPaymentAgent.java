package com.ssg.homework.t2021hw.payment.agent;

import com.ssg.homework.t2021hw.payment.server.PaymentServer;
import com.ssg.homework.t2021hw.payment.server.PaymentServerContainer;

public abstract class AbstractPaymentAgent implements PaymentAgent{

    private PaymentServerContainer container;

    public AbstractPaymentAgent(PaymentServerContainer container) {
        this.container = container;
    }

    @Override
    public PaymentServer getServer(String paymentCode, String paymentType) {
        return container.getActiveServer(paymentCode, paymentType);
    }
}
