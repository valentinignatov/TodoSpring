package com.example.sweater.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserWithNumberOfTodos {

    private String userName;

    private Long nrOfTodos;
}
