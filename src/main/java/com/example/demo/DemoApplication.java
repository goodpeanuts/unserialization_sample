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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @GetMapping("/admin/displayFile")
    @ResponseBody
    public String displayFile(HttpServletRequest request, Model model) {
        String fileName = request.getParameter("name");
        if (fileName != null && !fileName.isEmpty()) {
            File file = new File(request.getRealPath("/") + fileName);
            try (FileInputStream in = new FileInputStream(file)) {
                byte[] data = new byte[(int) file.length()];
                in.read(data);
                model.addAttribute("data", data);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Error reading file1");
            }
        } else {
            model.addAttribute("error", "File name not provided1.");
        }
        return "displayFile";
    }
}