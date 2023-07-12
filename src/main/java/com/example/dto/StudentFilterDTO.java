package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentFilterDTO {
//    id,name,surname,level,age,Gender, createdDateFrom,createdDateTo
    private Integer id;
    private String  name;
    private String  surname;
    private String level;
    private Integer age;
    private String gender;
    private LocalDateTime createdDateFrom;
    private LocalDateTime createdDateTo;

}
