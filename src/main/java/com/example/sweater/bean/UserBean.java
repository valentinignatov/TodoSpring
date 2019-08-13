package com.example.sweater.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserBean {

    private Long id;

    private String username;

    private Long nrOfTodos;

    private String createdOn;

}
