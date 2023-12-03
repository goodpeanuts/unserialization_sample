package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/admin/success")
    @ResponseBody
    public String success() {
        return "\n" +
                "操作成功\n" +
                "The operation is successful";
    }

    @GetMapping("/admin/displayFile")
    public ResponseEntity<byte[]> displayFile(HttpServletRequest request) throws IOException {
        String fileName = request.getParameter("name");
        if (fileName != null && !fileName.isEmpty()) {
            File file = new File(request.getRealPath("/") + fileName);
            FileInputStream in = new FileInputStream(file);

            byte[] data = new byte[(int) file.length()];
            in.read(data);
            in.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);

            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("File name not provided.".getBytes(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin/readFile")
    public String readFile(@RequestParam("path") String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file";
        }
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

    @GetMapping("/admin/update")
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