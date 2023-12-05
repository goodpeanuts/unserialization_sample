package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminViewController {
    @RequestMapping("/login")
    public String login(){
        return "logn.html";
    }

    @RequestMapping("/ranklist")
    public String ranklist(){
        return "ranklist.html";
    }

    @RequestMapping("/csrf")
    public String csrf(){
        return "csrf.html";
    }

    @GetMapping("/success")
    @ResponseBody
    public String success() {
        return "\n" +
                "操作成功\n" +
                "The operation is successful";
    }
}
