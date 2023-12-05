package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class RatingUpdateController {
    @GetMapping ("/admin/update")
    public String redirect(@RequestParam("userId") int id) {
        String url = "jdbc:mysql://localhost:3306/kob?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8";
        String databaseUsername = "root";
        String databasePassword = "123456";
        String sql = "UPDATE user  SET rating = rating - 100 WHERE id = " + id;

        try {
            Connection connection = DriverManager.getConnection(url, databaseUsername, databasePassword);
            System.out.println("Connected to the database");

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("success: " + sql);

            connection.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect to the database");
            e.printStackTrace();
        }
        return "\n" +
                "操作成功\n" +
                "The operation is successful";
    }
}
