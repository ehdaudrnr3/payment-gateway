package com.ssg.homework.t2021hw.payment.server;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaymentServer {

    private String paymentCode;
    private String paymentType;
    private String paymentName;
    private boolean canPatialCancel;

    public PaymentServer(String paymentCode, String paymentType, String paymentName, String partCnclYn) {
        this.paymentCode = paymentCode;
        this.paymentType = paymentType;
        this.paymentName = paymentName;
        this.canPatialCancel = partCnclYn.equalsIgnoreCase("Y") ? true : false;
    }

}
