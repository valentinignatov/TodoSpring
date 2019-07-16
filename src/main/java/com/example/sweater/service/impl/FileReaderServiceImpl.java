package com.example.sweater.service.impl;


import com.example.sweater.service.FileReaderService;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class FileReaderServiceImpl implements FileReaderService {

    private String read(){
        String path = "/home/valentin/Downloads/sweater/src/main/resources/query.txt";

        File file = new File(path);
        String result = "";

        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()){
            result = result.concat(scanner.nextLine());
        };

        scanner.close();
        return result;
    }
    public String initDatabase() {

        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/todos",
                    "postgres",
                    "558226");
            PreparedStatement statement = connection.prepareStatement(read());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
}
