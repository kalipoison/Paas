package com.gohb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.bo.SysLogBO;
import com.gohb.service.SysLogService;
import com.gohb.mapper.SysLogMapper;
import org.springframework.stereotype.Service;

/**
* @description 针对表【sys_log(系统日志)】的数据库操作Service实现
*/
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogBO>
    implements SysLogService{

}




