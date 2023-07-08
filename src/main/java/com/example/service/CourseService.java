package com.example.service;

import com.example.dto.CourseDTO;
import com.example.dto.StudentDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.exp.ItemNotFoundException;
import com.example.repository.CourseRepository;
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
public class CourseService  {
    @Autowired
    private CourseRepository courseRepository;

    public CourseDTO add(CourseDTO dto) {
        CourseEntity entity = toEntity(dto);
        courseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourseDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity ->{
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    public CourseDTO getById(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if(optional.isEmpty()){
            throw  new ItemNotFoundException("Student not found");
        }
        CourseEntity entity = optional.get();
        return toDTO(entity);
    }

    public Boolean update(Integer id, CourseDTO dto) {
        int affectedRows = courseRepository.update(id, dto.getName(), dto.getDuration(), dto.getPrice());
        return affectedRows == 1;
        /*Optional<CourseEntity> optional = courseRepository.findById(id);

        if (optional.isPresent()) {
            CourseEntity entity = optional.get();
            entity.setName(dto.getName());
            entity.setPrice(dto.getPrice());
            entity.setDuration(dto.getDuration());

            courseRepository.save(entity);
            return true;
        }
        return false;
*/}

    public Boolean delete(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if(optional.isPresent()){
            courseRepository.delete(optional.get());
            return true;
        }
        return false;
    }

    public List<CourseDTO> getByName(String name) {
        List<CourseEntity> entityList = courseRepository.getByName(name);
        return getCourseDTOS(entityList);
    }

    public List<CourseDTO> getByPrice(Double price) {
        List<CourseEntity> entityList = courseRepository.getByPrice(price);
        return getCourseDTOS(entityList);
    }

    public List<CourseDTO> getByDuration(String duration) {
        List<CourseEntity> entityList = courseRepository.getByDuration(duration);
        return getCourseDTOS(entityList);
    }

    public Object getByBetweenPrice(Double priceI, Double priceF) {
        List<CourseEntity> entityList = courseRepository.findByPriceBetween(priceI, priceF);
        return getCourseDTOS(entityList);
    }

    public List<CourseDTO> getByBetweenDate(LocalDate dateI, LocalDate dateF) {
        LocalDateTime from = LocalDateTime.of(dateI, LocalTime.MIN);
        LocalDateTime to = LocalDateTime.of(dateF, LocalTime.MAX);
        List<CourseEntity> entityList = courseRepository.findByCreatedDateBetween(from,to);
        return getCourseDTOS(entityList);
    }

    public PageImpl<CourseDTO> coursePagination(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.Direction.DESC, "id");
        Page<CourseEntity> pageObj = courseRepository.findAll(pageable);
        List<CourseDTO> studentDTOList = pageObj.stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(studentDTOList, pageable, pageObj.getTotalElements());

    }

    public PageImpl<CourseDTO> coursePaginationByDate(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.Direction.DESC, "createdDate");
        Page<CourseEntity> pageObj = courseRepository.findAll(pageable);
        List<CourseDTO> studentDTOList = pageObj.stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(studentDTOList, pageable, pageObj.getTotalElements());

    }

    public PageImpl<CourseDTO> coursePaginationByPrice(int page, int size, Double price){
        Pageable pageable = PageRequest.of(page,size);
        Page<CourseEntity> pageObj = courseRepository.findAllByPriceOrderByCreatedDate(price,pageable);
        List<CourseDTO> studentDTOList = pageObj.stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(studentDTOList, pageable, pageObj.getTotalElements());
    }

    public PageImpl<CourseDTO> coursePaginationByPrices(int page, int size, Double priceI,Double priceF){
        Pageable pageable = PageRequest.of(page,size);
        Page<CourseEntity> pageObj = courseRepository.findAllByPriceBetweenOrderByCreatedDate(priceI,priceF,pageable);
        List<CourseDTO> studentDTOList = pageObj.stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(studentDTOList, pageable, pageObj.getTotalElements());
    }





//    help

    public CourseDTO toDTO(CourseEntity entity){
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDuration(entity.getDuration());
        dto.setPrice(entity.getPrice());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public CourseEntity toEntity(CourseDTO dto){
        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        entity.setDuration(dto.getDuration());
        entity.setPrice(dto.getPrice());
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }

    private List<CourseDTO> getCourseDTOS(List<CourseEntity> entityList) {
        List<CourseDTO> dtoList = new LinkedList<>();
        if (entityList.isEmpty()) {
            throw new ItemNotFoundException("Student not found");
        }
        for (CourseEntity s:entityList){
            dtoList.add(toDTO(s));
        }
        return dtoList;
    }
}
