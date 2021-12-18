package com.gohb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/api/noauth/login")
    public String login(String username, String password) {
        return "login";
    }

    @RequestMapping("/api/noauth/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/api/auth/index")
    public String index1() {
        return "auth index";
    }

}
