package com.compasso.challenge.exception;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionError {
    private int status;
    private Map<String,String> message;
}
