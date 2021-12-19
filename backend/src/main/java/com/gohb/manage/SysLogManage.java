package com.gohb.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gohb.bo.SysLogBO;
import com.gohb.dto.SysLogDTO;

import java.util.List;

public interface SysLogManage {
    IPage<SysLogDTO> listSysLog(Page<SysLogBO> page, SysLogBO sysLogBO);

}
