package com.example.sweater.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTodoBean {

    @JsonIgnore
    private Long userId;

    private String text;

    private Long tagId;
}
