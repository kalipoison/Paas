package com.gohb.service.dashboard;

import com.gohb.params.bo.dashboard.SysDauBO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gohb.params.dto.dashboard.MonthQueryDTO;

import java.util.List;

/**
* @author zsh
* @description 针对表【sys_dau(访问量统计)】的数据库操作Service
* @createDate 2021-12-26 23:02:27
*/
public interface SysDauService extends IService<SysDauBO> {

    Integer totalQuery();

    List<Integer> getMonthQuery();
}
