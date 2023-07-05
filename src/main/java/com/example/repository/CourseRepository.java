package com.example.repository;

import com.example.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {
    List<CourseEntity> getByName(String name);

    List<CourseEntity> getByPrice(Double price);

    List<CourseEntity> getByDuration(String duration);

    List<CourseEntity> findByPriceBetween(Double priceI, Double priceF);

    List<CourseEntity> findByCreatedDateBetween(LocalDate dateI, LocalDate dateF);

}
