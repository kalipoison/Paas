package com.gohb.mapper.dashboard;

import com.gohb.params.bo.dashboard.SysDauBO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gohb.params.dto.dashboard.MonthQueryDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author zsh
* @description 针对表【sys_dau(访问量统计)】的数据库操作Mapper
* @createDate 2021-12-26 23:02:27
* @Entity com.gohb.entity.domain.SysDau
*/
public interface SysDauMapper extends BaseMapper<SysDauBO> {

    @Select("select sum(count) from sys_dau")
    Integer totalQuery();

    @Select("select sum(count) from sys_dau group by month")
    List<Integer> monthQuery();

}




