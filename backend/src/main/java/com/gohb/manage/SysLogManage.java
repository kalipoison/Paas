package com.gohb.manage;

import com.gohb.bo.SysLogBO;

import java.util.List;

public interface SysLogManage {

    List<SysLogBO> listSysLog(SysLogBO sysLogBO);

}
