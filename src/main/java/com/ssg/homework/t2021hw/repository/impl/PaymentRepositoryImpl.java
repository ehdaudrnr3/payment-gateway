package com.ssg.homework.t2021hw.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.homework.t2021hw.dto.PaymentDto;
import com.ssg.homework.t2021hw.repository.PaymentRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.ssg.homework.t2021hw.entity.QMember.member;
import static com.ssg.homework.t2021hw.entity.QPayment.payment;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * 결제내역 조회
     * @param mbrId
     * @param succYn
     * @param size
     * @return
     */
    @Override
    public List<PaymentDto> findPayments(String mbrId, String succYn, Integer size) {
        return queryFactory
                .select(Projections.constructor(PaymentDto.class, payment))
                .from(payment)
                .join(payment.member, member)
                .where(payment.member.mbrId.eq(mbrId), equalSuccYn(succYn))
                .orderBy(payment.pmtId.desc())
                .limit(defaultSize(size))
                .fetch();
    }

    private BooleanExpression equalSuccYn(String succYn) {
        return StringUtils.hasText(succYn) ? payment.succYn.eq(succYn) : null;
    }

    private Integer defaultSize(Integer size) {
        return size != null ? size : 15;
    }
}
