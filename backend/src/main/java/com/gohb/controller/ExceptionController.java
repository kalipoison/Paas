package com.gohb.controller;

import com.gohb.dto.Result;
import com.gohb.dto.ResultUtils;
import com.gohb.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 */
@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(value = SysException.class)
    public Result sysOperationHandler(SysException e) {
        return ResultUtils.getFailedResult(200, e.getMessage());
    }

}
