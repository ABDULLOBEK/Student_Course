package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class StudentCourseMarkFilterDTO {
    private Integer studentId;
    private Integer courseId;
    private Integer mark;
    private LocalDateTime createdDateFrom;
    private LocalDateTime createdDateTo;
}
