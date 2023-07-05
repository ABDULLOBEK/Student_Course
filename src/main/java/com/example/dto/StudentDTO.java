package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class StudentDTO {
    protected Integer id;
    private String name;
    private String surname;
    private String level;
    private Integer age;
    private String gender;
    private LocalDate createdDate;
}
