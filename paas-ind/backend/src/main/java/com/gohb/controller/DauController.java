package com.gohb.controller;

import com.gohb.manage.dashboard.DauManage;
import com.gohb.params.dto.dashboard.MonthQueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/auth/dau")
public class DauController {

    @Autowired
    private DauManage dauManage;

    @GetMapping("total")
    public Integer totalQuery() {
        return dauManage.totalQuery();
    }

    @GetMapping("month")
    public List<Integer> monthQuery() {
        return dauManage.getMonthQuery();
    }

}
