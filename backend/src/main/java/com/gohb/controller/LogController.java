package com.gohb.controller;

import com.gohb.bo.SysLogBO;
import com.gohb.dto.Result;
import com.gohb.dto.ResultUtils;
import com.gohb.dto.SysLogDTO;
import com.gohb.manage.SysLogManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private SysLogManage sysLogManage;

    @GetMapping("")
    public Result<List<SysLogDTO>> listLog(SysLogBO sysLogBO) {
        List<SysLogDTO> sysLogDTOS = sysLogManage.listSysLog(sysLogBO);
        return ResultUtils.getSuccessResult(sysLogDTOS);
    }

}
