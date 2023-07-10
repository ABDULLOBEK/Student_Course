package com.example.dto;

import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentCourseMarkDTO {
//    id,studentId,courseId,mark,createdDate
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Integer mark;
    private LocalDateTime createdDate;
}
