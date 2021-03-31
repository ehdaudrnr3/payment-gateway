package com.ssg.homework.t2021hw.payment.request;

import java.util.HashMap;
import java.util.Map;

public enum ApprovalType {
    APPROVE("10", "결제승인")
    , CANCEL("20", "결제취소")
    , PARTIAL_CANCEL("30", "결제 부분취소");

    private String code;
    private String text;

    private static Map<String, ApprovalType> approvalTypeMap = new HashMap<>();

    static {
        for(ApprovalType type : ApprovalType.values()) {
            approvalTypeMap.put(type.getCode(), type);
        }
    }

    ApprovalType(String code, String text) {
        this.code = code;
        this.text = text;
    }
    public static ApprovalType getType(String code) {
        return approvalTypeMap.get(code);
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
