package com.ssg.homework.t2021hw.exception;

import org.springframework.http.HttpStatus;

public class PaymentNotFoundException extends PaymentRuntimeException {
	private static final long serialVersionUID = 1L;

	private HttpStatus status = HttpStatus.UNAUTHORIZED;

	public PaymentNotFoundException(String message) {
		super(message);
	}

	public HttpStatus getStatus() {
		return status;
	}

}
