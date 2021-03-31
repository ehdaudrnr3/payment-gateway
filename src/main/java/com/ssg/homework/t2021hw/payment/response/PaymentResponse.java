package com.ssg.homework.t2021hw.payment.response;

import com.ssg.homework.t2021hw.payment.request.ApprovalType;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
public class PaymentResponse {

    private PaymentStatus status;
    private String paymentCode;
    private String paymentType;
    private String message;
    private Timestamp completeTime;

    @Builder
    public PaymentResponse(PaymentStatus status, String paymentCode, String paymentType, String message, Timestamp completeTime) {
        this.status = status;
        this.paymentCode = paymentCode;
        this.paymentType = paymentType;
        this.message = message;
        this.completeTime = completeTime;
    }

    public static PaymentResponse create(ApprovalType approvalType, PaymentStatus status, String paymentCode, String paymentType) {
        String message = "";
        if(status == PaymentStatus.SUCCESS) {
            message = approvalType.getText() + " 요청 성공";
        } else {
            message = approvalType.getText() + " 요청 실패";
        }
        return PaymentResponse.builder()
                .status(status)
                .paymentCode(paymentCode)
                .paymentType(paymentType)
                .completeTime(Timestamp.from(Instant.now()))
                .message(message)
                .build();
    }
}
