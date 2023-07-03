package com.payment.admin.common;

import lombok.Getter;


@Getter
public enum ResultCode implements IErrorCode{
    SUCCESS(200, "SUCCESS"),
    FAILED(500, "FAILED"),
    VALIDATE_FAILED(404, "VALIDATE_FAILED"),
    NOT_FOUND(404, "NOT_FOUND"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    UNAUTHENTICATED(401, "UNAUTHENTICATED"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    FORBIDDEN(403, "FORBIDDEN");

    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

}
