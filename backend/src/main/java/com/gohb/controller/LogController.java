package com.gohb.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gohb.params.bo.sys.SysLogBO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.sys.SysLogDTO;
import com.gohb.manage.sys.SysLogManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth/log")
public class LogController {

    @Autowired
    private SysLogManage sysLogManage;

    @GetMapping("")
    public Result<List<SysLogDTO>> listLog(Page<SysLogBO> page, SysLogBO sysLogBO) {
        IPage<SysLogDTO> sysLogDTOPage = sysLogManage.listSysLog(page,sysLogBO);
        return ResultUtils.getSuccessResult(sysLogDTOPage.getRecords()).setCount(sysLogDTOPage.getTotal());
    }

}
