package com.gohb.manage.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gohb.bo.sys.SysLogBO;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.dto.sys.SysLogDTO;
import com.gohb.manage.sys.SysLogManage;
import com.gohb.service.sys.SysLogService;
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
    public IPage<SysLogDTO> listSysLog(Page<SysLogBO> page, SysLogBO sysLogBO) {
        Page<SysLogBO> sysLogBOPage = sysLogService.page(page,new LambdaQueryWrapper<SysLogBO>()
                .eq(StringUtils.hasText(sysLogBO.getOperation()), SysLogBO::getOperation, sysLogBO.getOperation())
                .like(StringUtils.hasText(sysLogBO.getUsername()), SysLogBO::getUsername, sysLogBO.getUsername()));
        List<SysLogBO> sysLogBOS = sysLogBOPage.getRecords();
        List<SysLogDTO> sysLogDTOS = new ArrayList<>();
        for (SysLogBO logBO : sysLogBOS) {
            sysLogDTOS.add(BoToDtoUtils.sysLogBOTOSysLogDTO(logBO));
        }
        IPage<SysLogDTO> sysLogDTOPage = new Page<SysLogDTO>();
        sysLogDTOPage.setRecords(sysLogDTOS);
        sysLogDTOPage.setTotal(sysLogBOPage.getTotal());
        return sysLogDTOPage;
    }
}
