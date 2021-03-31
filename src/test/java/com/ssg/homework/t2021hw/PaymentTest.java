package com.ssg.homework.t2021hw;

import com.ssg.homework.t2021hw.dto.PaymentDto;
import com.ssg.homework.t2021hw.entity.Member;
import com.ssg.homework.t2021hw.payment.request.ApprovalType;
import com.ssg.homework.t2021hw.payment.request.PaymentRequest;
import com.ssg.homework.t2021hw.service.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.sql.Timestamp;
import java.time.Instant;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PaymentTest {

    @Mock
    PaymentService paymentService;

//    @Test
//    public void setup() {
//        Member member = createMember("1", "회원1");
//
//        PaymentRequest[] requests = createRequests(member);
//        PaymentDto[] paymentDtos = createDtos(member);
//
//        int length = requests.length;
//        for(int i = 0; i < length; i++) {
//            PaymentRequest request = requests[i];
//            PaymentDto paymentDto = paymentDtos[i];
//            when(paymentService.approve(request)).thenReturn(paymentDto);
//            paymentService.approve(request);
//
//            verify(paymentService, times(1)).approve(request);
//        }
//
//    }

    private Member createMember(String id, String name) {
        Member member = new Member();
        member.setMbrId(id);
        member.setName(name);
        return member;
    }

    private PaymentRequest[] createRequests(Member member) {
        return new PaymentRequest[] {
                PaymentRequest.ofApproval(member.getMbrId(), "P0001", "PT11", 1000),
                PaymentRequest.ofCancel(member.getMbrId(), "PMT001", 1000)
        };
    }

    private PaymentDto[] createDtos(Member member) {
        return new PaymentDto[] {
            createSuccessPaymentDto("PMT001", member.getMbrId(), "P0001", "PT11", 1000L, ApprovalType.APPROVE.getCode())
            , createFailPaymentDto("PMT002", member.getMbrId(), "P0001", "PT11", 1000L, ApprovalType.APPROVE.getCode())
        };
    }

    private PaymentDto createSuccessPaymentDto(String pmtId, String mbrId, String pmtCode, String pmtType, Long pmtAmt, String aprvType) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPmtId(pmtId);
        paymentDto.setMbrId(mbrId);
        paymentDto.setPmtCode(pmtCode);
        paymentDto.setPmtType(pmtType);
        paymentDto.setPmtAmt(pmtAmt);
        paymentDto.setSuccYn("Y");
        paymentDto.setSuccMsg("성공");
        paymentDto.setAprvType(aprvType);
        paymentDto.setAprvTime(Timestamp.from(Instant.now()));

        return paymentDto;
    }

    private PaymentDto createFailPaymentDto(String pmtId, String mbrId, String pmtCode, String pmtType, Long pmtAmt, String aprvType) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPmtId(pmtId);
        paymentDto.setMbrId(mbrId);
        paymentDto.setPmtCode(pmtCode);
        paymentDto.setPmtType(pmtType);
        paymentDto.setPmtAmt(pmtAmt);
        paymentDto.setSuccYn("N");
        paymentDto.setSuccMsg("실패");
        paymentDto.setAprvType(aprvType);
        paymentDto.setAprvTime(Timestamp.from(Instant.now()));

        return paymentDto;
    }
}
