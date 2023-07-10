package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "course")
@Getter
@Setter
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String  name;

    @Column(name = "price")
    private Double price;

    @Column(name = "duration")
    private String duration;

    @Column(name = "crated_date")
    private LocalDateTime createdDate;
}
