package com.qd.common.exception;


import com.qd.common.result.Result;
import com.qd.common.result.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result processNullException(Throwable cause, HttpServletRequest request, HttpServletResponse response) {
        cause.printStackTrace();
        return ResultUtils.returnFail("空指针异常");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result processException(Throwable cause, HttpServletRequest request, HttpServletResponse response) {
        cause.printStackTrace();
        return ResultUtils.returnFail();
    }

}