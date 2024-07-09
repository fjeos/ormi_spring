package org.example.basic;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import org.example.basic.day4.ValueTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = "org.example.basic.day4")
public class BasicApplication {

    @Autowired
    private ValueTest valueTest;

    @Value("${server.port}")
    private int port;

    @Value("${spring.application.name}")
    private String appName;

    @PostConstruct
    public void printConfig() {
        System.out.println("포트번호: " + port);
        System.out.println("애플리케이션 이름: " + appName);
    }
    public static void main(String[] args) throws ServletException, IOException {

        SpringApplication.run(BasicApplication.class, args);
    }
}