package com.example.repository;

import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {


    List<StudentEntity> getByName(String name);

    List<StudentEntity> getBySurname(String surname);

    List<StudentEntity> getByLevel(String level);

    List<StudentEntity> getByAge(Integer age);

    List<StudentEntity> getByGender(String gender);

    List<StudentEntity> findByCreatedDate(LocalDate localDateTime);

    List<StudentEntity> findByCreatedDateBetween(LocalDate startDateTime, LocalDate endDateTime);

//    
}
