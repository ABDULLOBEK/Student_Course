package com.example.service;

import com.example.dto.StudentDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.exp.ItemNotFoundException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO add(StudentDTO dto) {
        StudentEntity entity = toEntity(dto);
        studentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity ->{
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    public StudentDTO getById(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if(optional.isEmpty()){
            throw  new ItemNotFoundException("Student not found");
        }
        StudentEntity entity = optional.get();
        return toDTO(entity);
    }

    public Boolean update(Integer id, StudentDTO student) {
        Optional<StudentEntity> optional = studentRepository.findById(id);

        if (optional.isPresent()) {
            StudentEntity entity = optional.get();
            entity.setName(student.getName());
            entity.setSurname(student.getSurname());

            studentRepository.save(entity);
            return true;
        }
        return false;
    }

    public Boolean delete(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if(optional.isPresent()){
        studentRepository.delete(optional.get());
        return true;}
        return false;

    }

    public List<StudentDTO> getByName(String name) {
        List<StudentEntity> entityList = studentRepository.getByName(name);
        return getStudentDTOS(entityList);
    }

    public List<StudentDTO> getBySurname(String surname) {
        List<StudentEntity> entityList = studentRepository.getBySurname(surname);
        return getStudentDTOS(entityList);
    }

    public List<StudentDTO> getByLevel(String level) {
        List<StudentEntity> entityList = studentRepository.getByLevel(level);
        return getStudentDTOS(entityList);
    }

    public List<StudentDTO> getByAge(Integer age) {
        List<StudentEntity> entityList = studentRepository.getByAge(age);
        return getStudentDTOS(entityList);
    }

    public List<StudentDTO> getByGender(String gender) {
        List<StudentEntity> entityList = studentRepository.getByGender(gender);
        return getStudentDTOS(entityList);
    }

    public List<StudentDTO> getByDate(LocalDate date) {
        LocalDateTime from = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime to = LocalDateTime.of(date, LocalTime.MAX);
        List<StudentEntity> entityList = studentRepository.findByCreatedDateBetween(from, to);
        return getStudentDTOS(entityList);
    }

    public List<StudentDTO> getByBetweenDate(LocalDate dateI, LocalDate dateF) {
        LocalDateTime from = LocalDateTime.of(dateI, LocalTime.MIN);
        LocalDateTime to = LocalDateTime.of(dateF, LocalTime.MAX);
        List<StudentEntity> entityList = studentRepository.findByCreatedDateBetween(from,to);
        return getStudentDTOS(entityList);
    }

    public List<StudentDTO> studentPagination(int page, int size) {
        Pageable paging = PageRequest.of(page,size);
        Page<StudentEntity> pageObj = studentRepository.findAll(paging);
        List<StudentEntity> entityList = pageObj.getContent();
        Long totalCount = pageObj.getTotalElements();
        System.out.println();
        return  getStudentDTOS(entityList);
    }

    public List<StudentDTO> studentPaginationByLevel(int page, int size, String level){
        Pageable paging = PageRequest.of(page,size, Sort.by("id"));
        Page<StudentEntity> pageObj = studentRepository.findAllByLevel(level,paging);
        List<StudentEntity> entityList = pageObj.getContent();
        Long totalCount = pageObj.getTotalElements();
        System.out.println();
        return  getStudentDTOS(entityList);
    }

    public List<StudentDTO> studentPaginationByGender(int page, int size, String gender){
        Pageable paging = PageRequest.of(page,size);
        Page<StudentEntity> pageObj = studentRepository.findAllByGenderOOrderByCreatedDate(gender,paging);
        List<StudentEntity> entityList = pageObj.getContent();
        Long totalCount = pageObj.getTotalElements();
        System.out.println();
        return  getStudentDTOS(entityList);
    }




//  help

    public StudentDTO toDTO(StudentEntity entity){
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setGender(entity.getGender());
        dto.setLevel(entity.getLevel());
        return dto;
    }

    public StudentEntity toEntity(StudentDTO dto){
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setGender(dto.getGender());
        entity.setLevel(dto.getLevel());
        entity.setAge(dto.getAge());
        entity.setCreatedDate(LocalDate.now());
        return entity;
    }

    private List<StudentDTO> getStudentDTOS(List<StudentEntity> entityList) {
        List<StudentDTO> dtoList = new LinkedList<>();
        if (entityList.isEmpty()) {
            throw new ItemNotFoundException("Student not found");
        }
        for (StudentEntity s:entityList){
            dtoList.add(toDTO(s));
        }
        return dtoList;
    }
}
