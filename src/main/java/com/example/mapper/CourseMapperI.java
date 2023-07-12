package com.example.mapper;

import java.time.LocalDate;

public interface CourseMapperI {
//    id,name,price,duration,createdDateFrom,createdDateTo
    Integer getId();
    String getName();
    Double getPrice();
    String getDuration();
    LocalDate getCreatedDateFrom();
    LocalDate getCreatedDateTo();

}
