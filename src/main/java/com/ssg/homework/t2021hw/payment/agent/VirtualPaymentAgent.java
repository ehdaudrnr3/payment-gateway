package com.ssg.homework.t2021hw.payment.agent;

import com.ssg.homework.t2021hw.entity.Payment;
import com.ssg.homework.t2021hw.payment.request.PaymentRequest;
import com.ssg.homework.t2021hw.payment.response.PaymentResponse;
import com.ssg.homework.t2021hw.payment.response.PaymentStatus;
import com.ssg.homework.t2021hw.payment.server.PaymentServer;
import com.ssg.homework.t2021hw.payment.server.PaymentServerContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class VirtualPaymentAgent extends AbstractPaymentAgent {
    private final Logger logger = LoggerFactory.getLogger(AbstractPaymentAgent.class);

    private final Random random = new Random();
    private PaymentStatus[] states = {
            PaymentStatus.SUCCESS, PaymentStatus.SUCCESS, PaymentStatus.SUCCESS,
            PaymentStatus.SUCCESS, PaymentStatus.SUCCESS, PaymentStatus.SUCCESS,
            PaymentStatus.SUCCESS, PaymentStatus.SUCCESS, PaymentStatus.FAIL, PaymentStatus.FAIL
    };

    public VirtualPaymentAgent(PaymentServerContainer container) {
        super(container);
    }

    @Override
    public PaymentResponse payment(PaymentRequest request) {
        PaymentServer paymentServer = getServer(request.getPmtCode(), request.getPmtType());
        logger.info(paymentServer.toString());

        int index = random.nextInt(states.length);
        return PaymentResponse.create(request.getApprovalType(), states[index], paymentServer.getPaymentCode(), paymentServer.getPaymentType());
    }

    @Override
    public PaymentResponse cancel(Payment appprovePayment, PaymentRequest request) {
        PaymentServer paymentServer = getServer(appprovePayment.getPmtCode(), appprovePayment.getPmtType());
        logger.info(paymentServer.toString());

        int index = random.nextInt(states.length);
        return PaymentResponse.create(request.getApprovalType(), states[index], paymentServer.getPaymentCode(), paymentServer.getPaymentType());
    }
}
