package com.gohb.controller;

import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/api/noauth/login")
    public Result login(String username, String password) {
        return ResultUtils.getSuccessResult("login");
    }

    @RequestMapping("/api/noauth/index")
    public Result index() {
        return ResultUtils.getSuccessResult("index");
    }

    @RequestMapping("/api/auth/index")
    public String index1() {
        return "auth index";
    }

}
