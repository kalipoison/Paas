package com.gohb.controller;

import com.gohb.constant.StatusCodeConstant;
import com.gohb.manage.Client.MyUserManage;
import com.gohb.params.bo.client.MyUserBO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private MyUserManage myUserManage;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Result register(MyUserBO myUserBO) {
        myUserBO.setPassword(passwordEncoder.encode(myUserBO.getPassword()));
        Boolean register = myUserManage.register(myUserBO);
        if (!register) {
            ResultUtils.getFailedResult(StatusCodeConstant.createFail, "注册失败");
        }
        return ResultUtils.getSuccessResult("注册成功");
    }


}
