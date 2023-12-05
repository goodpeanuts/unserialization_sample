package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.ObjectInputStream;

@RestController
public class UnserializationController {
    // 反序列化接口
    @PostMapping("/admin/rmi")
    public String rmi(HttpServletRequest request) {
        try {
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
            Object obj = (Object) ois.readObject();
            return "unmarshal " + obj.getClass().getName() + " ok";
        } catch (ClassNotFoundException | IOException e) {
            return "unmarshal failed";
        }
    }
}
