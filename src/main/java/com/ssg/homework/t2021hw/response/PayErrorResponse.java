package com.ssg.homework.t2021hw.response;

import lombok.Getter;

@Getter
public class PayErrorResponse {

    private String message;

    public PayErrorResponse(String message) {
        this.message = message;
    }

}
