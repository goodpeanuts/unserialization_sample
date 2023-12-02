package com.example.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/admin/hello")
    public String hello() {
        return "hello world";
    }

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