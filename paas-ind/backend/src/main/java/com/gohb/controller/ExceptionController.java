package com.gohb.controller;

import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.exception.KubeException;
import com.gohb.params.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 */
@RestControllerAdvice
@Slf4j
public class ExceptionController {

    /**
     * 系统操作 sys user、role、userrole、rolemenu、menu操作异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = SysException.class)
    public Result sysOperationHandler(SysException e) {
        return ResultUtils.getFailedResult(200, e.getMessage());
    }

    /**
     * k8s 操作抛出异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = KubeException.class)
    public Result kubeOerationHandler(KubeException e) {
        return ResultUtils.getFailedResult(200, e.getMessage());
    }

}
