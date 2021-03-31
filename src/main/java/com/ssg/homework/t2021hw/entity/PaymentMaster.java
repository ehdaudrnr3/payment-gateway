package com.ssg.homework.t2021hw.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "PAYMENT_MST")
@Table(indexes = @Index(name = "index_payment_code", columnList = "pmtCode"))
public class PaymentMaster {

    private String pmtCode;

    private String pmtType;

    @Id
    private String pmtName;

    private String partCnclYn;

    public boolean canPartialCancel() {
        return partCnclYn != null && partCnclYn.equalsIgnoreCase("Y") ? true : false;
    }
}
