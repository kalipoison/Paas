package com.gohb.manage;

import com.gohb.bo.SysLogBO;
import com.gohb.dto.SysLogDTO;

import java.util.List;

public interface SysLogManage {

    List<SysLogDTO> listSysLog(SysLogBO sysLogBO);

}
