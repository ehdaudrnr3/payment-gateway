package com.ssg.homework.t2021hw.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class PaymentUnauthorizedException extends PaymentRuntimeException {
	private static final long serialVersionUID = 1L;

	private HttpStatus status = HttpStatus.UNAUTHORIZED;

	public PaymentUnauthorizedException(String message) {
		super(message);
	}

	public HttpStatus getStatus() {
		return status;
	}

}
