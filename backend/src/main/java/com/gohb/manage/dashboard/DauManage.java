package com.gohb.manage.dashboard;

import com.gohb.params.dto.dashboard.MonthQueryDTO;

import javax.annotation.ManagedBean;
import java.util.List;


public interface DauManage {

    Integer totalQuery();

    List<Integer> getMonthQuery();

}
