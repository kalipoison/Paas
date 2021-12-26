package com.gohb.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.params.bo.sys.SysLogBO;
import com.gohb.service.sys.SysLogService;
import com.gohb.mapper.sys.SysLogMapper;
import org.springframework.stereotype.Service;

/**
* @description 针对表【sys_log(系统日志)】的数据库操作Service实现
*/
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogBO>
    implements SysLogService{

}




