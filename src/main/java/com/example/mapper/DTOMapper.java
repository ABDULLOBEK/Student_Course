package com.example.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTOMapper {
//    response (id,student(id,name,surname),Course(id,name),mark,createdDate,)
    private Integer id;
    private Integer studentId;
    private String studentName;
    private String studentSurname;
    private Integer courseId;
    private String courseName;
    private Integer mark;
    private LocalDateTime createdDate;
}
