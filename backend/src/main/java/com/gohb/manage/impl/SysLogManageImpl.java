package com.gohb.manage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.bo.SysLogBO;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.dto.SysLogDTO;
import com.gohb.manage.SysLogManage;
import com.gohb.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class SysLogManageImpl implements SysLogManage {

    @Autowired
    private SysLogService sysLogService;


    @Override
    public List<SysLogDTO> listSysLog(SysLogBO sysLogBO) {
        List<SysLogBO> sysLogBOS = sysLogService.list(new LambdaQueryWrapper<SysLogBO>()
                .eq(StringUtils.hasText(sysLogBO.getOperation()), SysLogBO::getOperation, sysLogBO.getOperation())
                .like(StringUtils.hasText(sysLogBO.getUsername()), SysLogBO::getUsername, sysLogBO.getUsername()));
        List<SysLogDTO> sysLogDTOS = new ArrayList<>();
        for (SysLogBO logBO : sysLogBOS) {
            sysLogDTOS.add(BoToDtoUtils.sysLogBOTOSysLogDTO(logBO));
        }
        return sysLogDTOS;
    }
}
