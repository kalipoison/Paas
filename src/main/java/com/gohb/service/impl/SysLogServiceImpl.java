package com.gohb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.bo.SysLogBO;
import com.gohb.service.SysLogService;
import com.gohb.mapper.SysLogMapper;
import org.springframework.stereotype.Service;

/**
* @author zsh
* @description 针对表【sys_log(系统日志)】的数据库操作Service实现
* @createDate 2021-12-12 00:40:37
*/
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogBO>
    implements SysLogService{

}




