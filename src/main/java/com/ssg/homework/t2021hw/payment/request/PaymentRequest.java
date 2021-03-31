package com.ssg.homework.t2021hw.payment.request;

import com.ssg.homework.t2021hw.entity.Member;
import com.ssg.homework.t2021hw.entity.Payment;
import com.ssg.homework.t2021hw.payment.response.PaymentResponse;
import com.ssg.homework.t2021hw.payment.response.PaymentStatus;
import lombok.Getter;

@Getter
public class PaymentRequest {

    private ApprovalType approvalType;
    private String mbrId;
    private String pmtCode;
    private String pmtType;
    private String bfPmtId;
    private long pmtAmt;

    protected PaymentRequest(ApprovalType approvalType, String mbrId, String pmtCode, String pmtType, String bfPmtId, long pmtAmt) {
        this.approvalType = approvalType;
        this.mbrId = mbrId;
        this.pmtCode = pmtCode;
        this.pmtType = pmtType;
        this.bfPmtId = bfPmtId;
        this.pmtAmt = pmtAmt;
    }

    public static PaymentRequest of(ApprovalType approvalType, String mbrId, String pmtCode, String pmtType, String bfPmtId, long pmtAmt) {
        return new PaymentRequest(approvalType, mbrId, pmtCode, pmtType, bfPmtId, pmtAmt);
    }

    public static PaymentRequest ofApproval(String mbrId, String pmtCode, String pmtType, long pmtAmt) {
        return of(ApprovalType.APPROVE, mbrId, pmtCode, pmtType, null, pmtAmt);
    }

    public static PaymentRequest ofCancel(String mbrId, String bfPmtId, long pmtAmt) {
        return of(ApprovalType.CANCEL, mbrId, null, null, bfPmtId, pmtAmt);
    }

    public static PaymentRequest ofPartialCancel(String mbrId, String bfPmtId, long pmtAmt) {
        return of(ApprovalType.PARTIAL_CANCEL, mbrId, null, null, bfPmtId, pmtAmt);
    }

    public Payment toApproveEntity(Member member, PaymentResponse response) {
        String yesNo = response.getStatus() == PaymentStatus.SUCCESS ? "Y" : "N";
        return Payment.builder()
                .member(member)
                .bf_pmt_id(bfPmtId)
                .pmtCode(response.getPaymentCode())
                .pmtType(response.getPaymentType())
                .pmtAmt(pmtAmt)
                .aprvType(approvalType.getCode())
                .aprvTime(response.getCompleteTime())
                .succMsg(response.getMessage())
                .succYn(yesNo)
                .build();
    }

    public Payment toCancelEntity(Member member, Payment beforePayment, PaymentResponse response) {
        String yesNo = response.getStatus() == PaymentStatus.SUCCESS ? "Y" : "N";
        return Payment.builder()
                .member(member)
                .pmtCode(beforePayment.getPmtCode())
                .pmtType(beforePayment.getPmtType())
                .bf_pmt_id(bfPmtId)
                .pmtAmt(pmtAmt)
                .aprvType(approvalType.getCode())
                .aprvTime(response.getCompleteTime())
                .succMsg(response.getMessage())
                .succYn(yesNo)
                .build();
    }
}
