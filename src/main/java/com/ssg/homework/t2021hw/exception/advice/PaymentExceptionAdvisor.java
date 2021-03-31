package com.ssg.homework.t2021hw.exception.advice;

import com.ssg.homework.t2021hw.exception.PaymentNotFoundException;
import com.ssg.homework.t2021hw.exception.PaymentUnauthorizedException;
import com.ssg.homework.t2021hw.response.PaymentResponse;
import com.ssg.homework.t2021hw.response.PaymentResponseHeader;
import com.ssg.homework.t2021hw.response.PayErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentExceptionAdvisor {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity missingRequestParameterHandle(MissingServletRequestParameterException e) {
        PayErrorResponse body = new PayErrorResponse(e.getMessage());
        PaymentResponse response = PaymentResponse.of(PaymentResponseHeader.create(HttpStatus.UNAUTHORIZED), body);
        return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({PaymentNotFoundException.class})
    public ResponseEntity notFoundHandle(PaymentNotFoundException e) {
        PayErrorResponse body = new PayErrorResponse(e.getMessage());
        PaymentResponse response = PaymentResponse.of(PaymentResponseHeader.create(e.getStatus()), body);
        return new ResponseEntity(response, e.getStatus());
    }

    @ExceptionHandler({PaymentUnauthorizedException.class,})
    public ResponseEntity unauthorizedHandle(PaymentUnauthorizedException e) {
        PayErrorResponse body = new PayErrorResponse(e.getMessage());
        PaymentResponse response = PaymentResponse.of(PaymentResponseHeader.create(e.getStatus()), body);
        return new ResponseEntity(response, e.getStatus());
    }
}
