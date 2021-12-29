package com.gohb.manage.dashboard.impl;

import com.gohb.manage.dashboard.DauManage;
import com.gohb.params.dto.dashboard.MonthQueryDTO;
import com.gohb.service.dashboard.SysDauService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.List;

@ManagedBean
public class DauManageImpl implements DauManage {

    @Autowired
    private SysDauService sysDauService;


    @Override
    public Integer totalQuery() {
        return sysDauService.totalQuery();
    }

    @Override
    public List<Integer> getMonthQuery() {
        return sysDauService.getMonthQuery();
    }

}
