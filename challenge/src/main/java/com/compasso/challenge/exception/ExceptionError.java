package com.compasso.challenge.exception;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionError {
    private int status;
    private Map<String,List<String>> message;
}
