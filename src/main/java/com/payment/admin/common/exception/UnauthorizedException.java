package com.payment.admin.common.exception;

import com.payment.admin.common.IErrorCode;
import com.payment.admin.common.ResultCode;
import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {

    private final IErrorCode errorCode;
    public UnauthorizedException() {
        super(ResultCode.UNAUTHORIZED.getMessage());
        this.errorCode = ResultCode.UNAUTHORIZED;
    }

}
