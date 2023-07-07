package com.example.repository;

import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer>, PagingAndSortingRepository<CourseEntity, Integer> {
    List<CourseEntity> getByName(String name);

    List<CourseEntity> getByPrice(Double price);

    List<CourseEntity> getByDuration(String duration);

    List<CourseEntity> findByPriceBetween(Double priceI, Double priceF);

    List<CourseEntity> findByCreatedDateBetween(LocalDateTime dateI, LocalDateTime dateF);

    Page<CourseEntity> findAllByPriceOrderByCreatedDate(Double price, Pageable pageable);

    Page<CourseEntity> findAllByPriceBetweenOrderByCreatedDate(Double priceI, Double priceF, Pageable pageable);



}
