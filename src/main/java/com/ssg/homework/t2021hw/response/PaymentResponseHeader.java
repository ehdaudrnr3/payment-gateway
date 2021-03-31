package com.ssg.homework.t2021hw.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PaymentResponseHeader {

    private String status;
    private int statusCode;

    public PaymentResponseHeader(String status, int statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }

    public static PaymentResponseHeader create(HttpStatus status) {
        return new PaymentResponseHeader(status.getReasonPhrase(), status.value());
    }
}
