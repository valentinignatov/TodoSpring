package com.example.sweater.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBean {

    private Long id;

    private String username;

    private Long nrOfTodos;

    private String createdOn;

}
