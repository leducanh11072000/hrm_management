package com.payment.admin.common.exception;

import com.payment.admin.common.IErrorCode;


public class UnauthenticatedException extends RuntimeException {

    private IErrorCode errorCode;

    public UnauthenticatedException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public UnauthenticatedException(String message) {
        super(message);
    }

    public UnauthenticatedException(Throwable cause) {
        super(cause);
    }

    public UnauthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
    public UnauthenticatedException() {
        super("UnauthorizedException");
    }
}
