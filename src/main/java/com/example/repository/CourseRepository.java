package com.example.repository;

import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer>, PagingAndSortingRepository<CourseEntity, Integer> {

    //2
    @Query("from CourseEntity where id=:id")
    Optional<CourseEntity> findById(@Param("id") Integer id);

    //3
    @Query("from CourseEntity ")//select * from student
    List<CourseEntity> findAll();

    //4
    @Transactional
    @Modifying
    @Query("update CourseEntity as s set s.name =:name, s.price=:price, s.duration=:duration where s.id  =:id ")
    int update(@Param("id") Integer id, @Param("name") String name, @Param("duration") String duration, @Param("price") Double price);

    //5
    @Transactional
    @Modifying
    @Query("delete from CourseEntity as s where s.id=:id ")
    int delete(@Param("id") Integer id);

    //6
    @Query(" from CourseEntity as s where s.name =?1") // select * from course where s.name = ?
    List<CourseEntity> getByName(String name);
    //6
    @Query(" from CourseEntity as s where s.price =?1")
    List<CourseEntity> getByPrice(Double price);
    //6
    @Query(" from CourseEntity as s where s.duration =?1")
    List<CourseEntity> getByDuration(String duration);

    //7
    @Query("select s from CourseEntity as s where s.price >:priceI and s.price<:priceF")
    List<CourseEntity> findByPriceBetween(@Param("priceI") Double priceI, @Param("priceF") Double priceF);

    //8
    @Query("SELECT s FROM CourseEntity s WHERE s.createdDate > :startDateTime AND s.createdDate <= :endDateTime")
    List<CourseEntity> findByCreatedDateBetween(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

    //10
    Page<CourseEntity> findAllByPriceOrderByCreatedDate(Double price, Pageable pageable);

    //11
    Page<CourseEntity> findAllByPriceBetweenOrderByCreatedDate(Double priceI, Double priceF, Pageable pageable);




}
