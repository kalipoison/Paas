package com.gohb.manage.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gohb.bo.sys.SysLogBO;
import com.gohb.dto.SysLogDTO;

public interface SysLogManage {
    IPage<SysLogDTO> listSysLog(Page<SysLogBO> page, SysLogBO sysLogBO);

}
