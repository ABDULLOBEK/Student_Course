package com.example.service;

import com.example.mapper.DTOMapper;
import com.example.mapper.SCMMapper;
import com.example.dto.StudentCourseMarkDTO;
import com.example.entity.StudentCourseMarkEntity;
import com.example.exp.ItemNotFoundException;
import com.example.repository.StudentCourseMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository studentCourseMarkRepository;

    public StudentCourseMarkDTO add(StudentCourseMarkDTO dto) {
        StudentCourseMarkEntity entity = toEntity(dto);
        studentCourseMarkRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean update(Integer id, StudentCourseMarkDTO scm) {
        int affectedRows = studentCourseMarkRepository.updateMark(id,scm.getMark());
        return affectedRows == 1;
    }

    public SCMMapper getById(Integer id) {
        Optional<StudentCourseMarkEntity> optional = studentCourseMarkRepository.findById(id);
        if(optional.isEmpty()){
            throw  new ItemNotFoundException("Student not found");
        }
        StudentCourseMarkEntity entity = optional.get();
        return new SCMMapper(entity.getId(),entity.getStudent().getId(), entity.getCourse().getId(),entity.getMark(),entity.getCreatedDate());
    }

    public DTOMapper getByIdDTO(Integer id) {
        Optional<StudentCourseMarkEntity> optional = studentCourseMarkRepository.findById(id);
        if(optional.isEmpty()){
            throw  new ItemNotFoundException("Student not found");
        }
        StudentCourseMarkEntity entity = optional.get();
        return new DTOMapper(entity.getId(),
                   entity.getStudent().getId(),entity.getStudent().getName(),entity.getStudent().getSurname(),
                   entity.getCourse().getId(),entity.getCourse().getName(),entity.getMark(),entity.getCreatedDate());
    }

    public Integer delete(Integer id) {
        return studentCourseMarkRepository.delete(id);

    }

    public List<StudentCourseMarkDTO> getAll() {
        Iterable<StudentCourseMarkEntity> iterable = studentCourseMarkRepository.findAll();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity ->{
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    public List<Integer> getMarkByDate(LocalDate dateI, LocalDate dateF) {
        LocalDateTime from = LocalDateTime.of(dateI, LocalTime.MIN);
        LocalDateTime to = LocalDateTime.of(dateF, LocalTime.MAX);
        return studentCourseMarkRepository.findByCreatedDateBetween(from,to);
    }

    public List<Integer> getMarkByTwoDay(LocalDate dateI, LocalDate dateF) {
        LocalDateTime from = LocalDateTime.of(dateI, LocalTime.MIN);
        LocalDateTime to = LocalDateTime.of(dateF, LocalTime.MAX);
        return studentCourseMarkRepository.findByCreatedDateBetween(from,to);
    }

    public List<Integer> getAllMark() {
        return studentCourseMarkRepository.markListDesc();
    }

    public List<Integer> markListByCourse(Integer course_id){
        return studentCourseMarkRepository.markListByCourse(course_id);
    }

    public Integer lastMark(){
        return studentCourseMarkRepository.lastMark();
    }

    public List<Integer> bigThreeMark(){
        return studentCourseMarkRepository.bigThreeMark();
    }

    public Integer firstMark(){
        return studentCourseMarkRepository.firstMark();
    }

    public Integer firstMarkByCourse(Integer course_id){
        return studentCourseMarkRepository.firstMarkByCourse(course_id);
    }

    public Integer highestMarkByCourse(Integer course_id){
        return studentCourseMarkRepository.highestMarkByCourse(course_id);
    }

    public Double avgMark(){
        return studentCourseMarkRepository.avgMark();
    }

    public Double avbByCourse(Integer course_id){
        return studentCourseMarkRepository.avbByCourse(course_id);
    }

    public Integer maxMarkCountByCourse(Integer course_id){
        return studentCourseMarkRepository.maxMarkCountByCourse(course_id);
    }

    public Integer maxMarkByCourse(Integer course_id){
        return studentCourseMarkRepository.maxMarkByCourse(course_id);
    }

    public Double avgMarkByCourse(Integer course_id){
        return studentCourseMarkRepository.avgMarkByCourse(course_id);
    }

    public Integer countMarkByCourse(Integer course_id){
        return studentCourseMarkRepository.countMarkByCourse(course_id);
    }

    public PageImpl<StudentCourseMarkDTO> studentCourseMarkPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.Direction.DESC, "id");
        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.findAll(pageable);
        List<StudentCourseMarkDTO> studentCourseMarkDTOList = pageObj.stream().map(this::toDTO).toList();
        return new PageImpl<>(studentCourseMarkDTOList, pageable, pageObj.getTotalElements());

    }

    public PageImpl<StudentCourseMarkDTO> findAllByStudentOrderByCreatedDate(int student_id,int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.findAllByStudentOrderByCreatedDate(student_id,pageable);
        List<StudentCourseMarkDTO> studentCourseMarkDTOList = pageObj.stream().map(this::toDTO).toList();
        return new PageImpl<>(studentCourseMarkDTOList, pageable, pageObj.getTotalElements());
    }

    public PageImpl<StudentCourseMarkDTO> findAllByCourseOrderByCreatedDate(int course_id,int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.findAllByCourseOrderByCreatedDate(course_id,pageable);
        List<StudentCourseMarkDTO> studentCourseMarkDTOList = pageObj.stream().map(this::toDTO).toList();
        return new PageImpl<>(studentCourseMarkDTOList, pageable, pageObj.getTotalElements());
    }




    public StudentCourseMarkDTO toDTO(StudentCourseMarkEntity entity) {
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourse());
        dto.setStudentId(entity.getStudent());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public StudentCourseMarkEntity toEntity(StudentCourseMarkDTO dto) {
        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCourse(dto.getCourseId());
        entity.setStudent(dto.getStudentId());
        entity.setMark(dto.getMark());
        return entity;
    }
}
