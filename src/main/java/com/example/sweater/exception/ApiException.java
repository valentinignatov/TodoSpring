package com.example.sweater.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiException {
    private String message;
    @JsonIgnore private transient Throwable throwable;
    private HttpStatus httpStatus;
    private ZonedDateTime timeStamp;
}
