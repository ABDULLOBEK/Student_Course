package com.example.controller;

import com.example.exp.ItemNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ItemNotFoundException.class)
    public  ResponseEntity<String> handler(ItemNotFoundException e){
        e.printStackTrace();
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
