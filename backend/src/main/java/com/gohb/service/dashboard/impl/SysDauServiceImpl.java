package com.gohb.service.dashboard.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.params.bo.dashboard.SysDauBO;
import com.gohb.params.dto.dashboard.MonthQueryDTO;
import com.gohb.service.dashboard.SysDauService;
import com.gohb.mapper.dashboard.SysDauMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author zsh
* @description 针对表【sys_dau(访问量统计)】的数据库操作Service实现
* @createDate 2021-12-26 23:02:27
*/
@Service
public class SysDauServiceImpl extends ServiceImpl<SysDauMapper, SysDauBO>
    implements SysDauService{

    @Autowired
    private SysDauMapper sysDauMapper;

    @Override
    public Integer totalQuery() {
        return sysDauMapper.totalQuery();
    }

    @Override
    public List<Integer> getMonthQuery() {
        return sysDauMapper.monthQuery();
    }
}




