package com.payment.admin.config;

import com.payment.admin.common.CommonResult;
import com.payment.admin.common.exception.ApiException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public Object handleException1(ApiException e) {
        return CommonResult.failed(e.getMessage());
    }


    @ExceptionHandler({MissingServletRequestParameterException.class,
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class
    })
    @ResponseBody
    public Object handleException12() {
        return CommonResult.badRequest("Please check param!");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public Object res_404() {
        return CommonResult.notFound();
    }
}
