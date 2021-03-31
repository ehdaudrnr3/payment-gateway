package com.ssg.homework.t2021hw.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.homework.t2021hw.entity.PaymentMaster;
import com.ssg.homework.t2021hw.repository.PaymentMasterRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.ssg.homework.t2021hw.entity.QPaymentMaster.paymentMaster;

@Repository
@RequiredArgsConstructor
public class PaymentMasterRepositoryImpl implements PaymentMasterRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * 결제코드, 결제타입에 따른 결제 코드 정보조회
     * @param pmtCode
     * @param pmtType
     * @return
     */
    @Override
    public List<PaymentMaster> findListByPmtCodeAndAndPmtType(String pmtCode, String pmtType) {
        return queryFactory
            .select(paymentMaster)
            .from(paymentMaster)
            .where(paymentMaster.pmtCode.eq(pmtCode)
                    .and(equalPmtType(pmtType))
            ).fetch();

    }

    /**
     * 결제타입 null처리
     * @param pmtType
     * @return
     */
    private BooleanExpression equalPmtType(String pmtType) {
        return StringUtils.hasText(pmtType) ? paymentMaster.pmtType.eq(pmtType) : null;
    }
}
