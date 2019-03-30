package com.example.capp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.capp.dao")
public class CappApplication {

    public static void main(String[] args) {
        SpringApplication.run(CappApplication.class, args);
    }

}
