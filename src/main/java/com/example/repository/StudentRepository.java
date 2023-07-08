package com.example.repository;

import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Parent;
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

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>, PagingAndSortingRepository<StudentEntity, Integer> {

    //2
    @Query("from StudentEntity ")//select * from student
    List<StudentEntity> findAll();

    //3
    @Query("from StudentEntity where id=:id")
    Optional<StudentEntity> findById(@Param("id") Integer id);

    //4
    @Transactional
    @Modifying
    @Query("update StudentEntity as s set s.name =:name, s.surname=:surname where s.id  =:id ")
    int updateNameAndSurname(@Param("id") Integer id, @Param("name") String name, @Param("surname") String surname);

    //5
    @Transactional
    @Modifying
    @Query("delete from StudentEntity as s where s.id=:id ")
    int delete(@Param("id") Integer id);

    //6
    @Query(" from StudentEntity as s where s.name =?1") // select * from student where s.name = ?
    List<StudentEntity> getByName( String name);
    //6
    @Query(" from StudentEntity as s where s.surname =:surname ") // all is being taken
    List<StudentEntity> getBySurname(@Param("surname") String surname);
    //6
    @Query("from StudentEntity as s where s.level=:level ")
    List<StudentEntity> getByLevel(@Param("level") String level);
    //6
    @Query("from StudentEntity as s where s.age =: age")
    List<StudentEntity> getByAge(@Param("age") Integer age);
    //6
    @Query("from StudentEntity as s where s.gender=:gender")
    List<StudentEntity> getByGender(@Param("gender") String gender);

    //7,8
    @Query("SELECT s FROM StudentEntity s WHERE s.createdDate > :startDateTime AND s.createdDate <= :endDateTime")
    List<StudentEntity> findByCreatedDateBetween(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);


    //10
    Page<StudentEntity> findAllByLevel(String level, Pageable pageable);

    //11
    Page<StudentEntity> findAllByGenderOrderByCreatedDate(String gender, Pageable pageable);


//    
}
