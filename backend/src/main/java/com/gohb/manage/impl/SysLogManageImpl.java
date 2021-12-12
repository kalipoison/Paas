package com.gohb.manage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.bo.SysLogBO;
import com.gohb.manage.SysLogManage;
import com.gohb.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.List;

@ManagedBean
public class SysLogManageImpl implements SysLogManage {

    @Autowired
    private SysLogService sysLogService;


    @Override
    public List<SysLogBO> listSysLog(SysLogBO sysLogBO) {
        List<SysLogBO> sysLogBOS = sysLogService.list(new LambdaQueryWrapper<SysLogBO>()
                .eq(StringUtils.hasText(sysLogBO.getOperation()), SysLogBO::getOperation, sysLogBO.getOperation())
                .like(StringUtils.hasText(sysLogBO.getUsername()), SysLogBO::getUsername, sysLogBO.getUsername()));
        return sysLogBOS;
    }
}
