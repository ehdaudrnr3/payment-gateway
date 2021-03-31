package com.ssg.homework.t2021hw.exception;

import org.springframework.http.HttpStatus;

public class PaymentRuntimeException extends RuntimeException {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public PaymentRuntimeException() {
    }

    public PaymentRuntimeException(HttpStatus status) {
        this.status = status;
    }

    public PaymentRuntimeException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
