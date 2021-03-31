package com.ssg.homework.t2021hw.entity;

import com.ssg.homework.t2021hw.entity.generator.StringPrefixSequenceIdGenerator;
import com.ssg.homework.t2021hw.exception.PaymentNotFoundException;
import com.ssg.homework.t2021hw.exception.PaymentUnauthorizedException;
import com.ssg.homework.t2021hw.payment.request.ApprovalType;
import com.ssg.homework.t2021hw.payment.request.PaymentRequest;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(indexes = @Index(name = "index_member", columnList = "mbr_id"))
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "string_prefix_generator")
    @GenericGenerator(
            name = "string_prefix_generator",
            strategy = StringPrefixSequenceIdGenerator.CLASS_NAME,
            parameters = {
                @Parameter(name = StringPrefixSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                @Parameter(name = StringPrefixSequenceIdGenerator.PREFIX_PARAMETER, value = "PMT"),
                @Parameter(name = StringPrefixSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%07d")
            }
    )
    private String pmtId;

    @ManyToOne(fetch = FetchType.LAZY,  targetEntity = Member.class)
    @JoinColumn(name = "mbr_id")
    private Member member;

    private String bf_pmt_id;

    private String pmtCode;

    private String pmtType;

    private String succYn;

    private String succMsg;

    private String aprvType;

    private Timestamp aprvTime;

    private Long pmtAmt;

    @Builder
    public Payment(Member member, String bf_pmt_id, String pmtCode, String pmtType, String succYn, String succMsg, String aprvType, Timestamp aprvTime, Long pmtAmt) {
        this.member = member;
        this.bf_pmt_id = bf_pmt_id;
        this.pmtCode = pmtCode;
        this.pmtType = pmtType;
        this.succYn = succYn;
        this.succMsg = succMsg;
        this.aprvType = aprvType;
        this.aprvTime = aprvTime;
        this.pmtAmt = pmtAmt;
    }

    /**
     * 결제취소 검증
     * @param request
     * @param paymentMaster
     * @return
     */
    public boolean availableCancel(PaymentRequest request, PaymentMaster paymentMaster) {
        if(ApprovalType.getType(this.aprvType) != ApprovalType.APPROVE) {
            throw new PaymentUnauthorizedException("can only approved payment");
        }
        if(!member.getMbrId().equals(request.getMbrId())) {
            throw new PaymentUnauthorizedException("invalid request member id");
        }
        if(request.getApprovalType() == ApprovalType.CANCEL) {
            if(getPmtAmt().compareTo(request.getPmtAmt()) != 0) {
                throw new PaymentNotFoundException("Approval amount and cancellation amount are different");
            }
        }
        if(request.getApprovalType() == ApprovalType.PARTIAL_CANCEL) {
            if(getPmtAmt().compareTo(request.getPmtAmt()) <= 0) {
                throw new PaymentNotFoundException("Approval amount not enough");
            }
            if(!paymentMaster.canPartialCancel()) {
                throw new PaymentUnauthorizedException("Partial cancelleation is not possible");
            }
        }
        return true;
    }

}
