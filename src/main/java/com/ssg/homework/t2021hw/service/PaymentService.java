package com.ssg.homework.t2021hw.service;

import com.ssg.homework.t2021hw.dto.PaymentDto;
import com.ssg.homework.t2021hw.entity.Member;
import com.ssg.homework.t2021hw.entity.Payment;
import com.ssg.homework.t2021hw.entity.PaymentMaster;
import com.ssg.homework.t2021hw.exception.PaymentNotFoundException;
import com.ssg.homework.t2021hw.payment.agent.PaymentAgent;
import com.ssg.homework.t2021hw.payment.request.PaymentRequest;
import com.ssg.homework.t2021hw.payment.response.PaymentResponse;
import com.ssg.homework.t2021hw.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final MemberService memberService;
    private final PaymentMasterService paymentMasterService;
    private final PaymentRepository paymentRepository;
    private final PaymentAgent paymentAgent;

    /**
     * 결제승인
     * @param request
     * @return
     */
    public PaymentDto approve(PaymentRequest request) {
        Member member = memberService.findMember(request.getMbrId());
        if(!validatePaymentMaster(request)) {
            throw new PaymentNotFoundException("invalid paymentCode or paymentType");
        }

        PaymentResponse response = paymentAgent.payment(request);
        Payment payment = request.toApproveEntity(member, response);
        paymentRepository.save(payment);
        return PaymentDto.of(payment);
    }

    /**
     * 결제 취소, 부분취소
     * @param request
     * @return
     */
    public PaymentDto cancel(PaymentRequest request) {
        Payment approvePayment = paymentRepository.findById(request.getBfPmtId())
                .orElseThrow(() -> new PaymentNotFoundException("can't not found approve payment id"));
        PaymentMaster paymentMaster = paymentMasterService.getPaymentMaster(approvePayment.getPmtCode(), approvePayment.getPmtType());

        Payment payment = null;
        if(approvePayment.availableCancel(request, paymentMaster)) {
            Member member = memberService.findMember(request.getMbrId());

            PaymentResponse response = paymentAgent.cancel(approvePayment, request);
            payment = request.toCancelEntity(member, approvePayment, response);
            paymentRepository.save(payment);
        }
        return PaymentDto.of(payment);
    }

    /**
     * 결제내역 조회
     * @param mbrId
     * @param succYn
     * @param size
     * @return
     */
    public List<PaymentDto> getRecentPayments(String mbrId, String succYn, Integer size) {
        return paymentRepository.findPayments(mbrId, succYn, size);
    }

    /**
     * 유효한 결제 코드 검증
     * @param request
     * @return
     */
    private boolean validatePaymentMaster(PaymentRequest request) {
        List<PaymentMaster> paymentMasters = paymentMasterService.getPaymentMasters(request.getPmtCode(), request.getPmtType());
        return !paymentMasters.isEmpty();
    }

}
