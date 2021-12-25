package com.gohb.manage.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gohb.params.bo.sys.SysLogBO;
import com.gohb.params.dto.sys.SysLogDTO;

public interface SysLogManage {
    IPage<SysLogDTO> listSysLog(Page<SysLogBO> page, SysLogBO sysLogBO);

}
