package com.example.sweater.repository.custom.impl;

import com.example.sweater.repository.custom.TagCustomRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TagCustomRepositoryImpl implements TagCustomRepository {
    private Connection connection;

    private Statement stmt = null;

    public TagCustomRepositoryImpl() {

        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/todos",
                    "postgres",
                    "558226");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Long> findIdByName(String tag) {

        List<Long> temp = new ArrayList<>();

        String sql = "select id from tags where tag_name like ?;";

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, "%" + tag + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                temp.add(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return temp;
    }
}
