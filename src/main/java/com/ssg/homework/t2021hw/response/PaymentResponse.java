package com.ssg.homework.t2021hw.response;

import lombok.Getter;

@Getter
public class PaymentResponse<T> {

    private PaymentResponseHeader header;
    private T body;

    public PaymentResponse(PaymentResponseHeader header, T body) {
        this.header = header;
        this.body = body;
    }

    public static <T> PaymentResponse of(PaymentResponseHeader header, T body) {
        return new PaymentResponse<>(header, body);
    }
}
