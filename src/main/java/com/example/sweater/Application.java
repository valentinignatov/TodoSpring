package com.example.sweater;

import com.example.sweater.service.FileReaderService;
import com.example.sweater.service.impl.FileReaderServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        fileReaderService.initDatabase();
        SpringApplication.run(Application.class, args);
    }
}