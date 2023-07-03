package com.payment.admin.common.exception;

public class ApiException extends RuntimeException {

    public ApiException(Exception e) {
        super(e.getMessage());
    }

    public ApiException(String message) {
        super(message);
    }
}
