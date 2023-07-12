package com.example.repository;

import com.example.dto.CourseFilterDTO;
import com.example.dto.StudentFilterDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomStudentRepository {
    @Autowired
    private EntityManager entityManager;

    public List<StudentEntity> filter(StudentFilterDTO filterDTO){
        StringBuilder stringBuilder = new StringBuilder("select c from StudentEntity as c where 1=1 ");
        Map<String, Object> params = new HashMap<>();
        if(filterDTO.getId()!=null){
            stringBuilder.append(" and c.id =:id ");
            params.put("id",filterDTO.getId());
        }
        if(filterDTO.getName()!=null){
            stringBuilder.append(" and lower(c.name) =:name ");
            params.put("name",filterDTO.getName().toLowerCase());
        }
        if(filterDTO.getSurname()!=null){
            stringBuilder.append(" and lower(c.surname) =:surname ");
            params.put("surname",filterDTO.getSurname().toLowerCase());
        }
        if(filterDTO.getLevel()!=null){
            stringBuilder.append("and lower(c.level)=:level");
            params.put("level", filterDTO.getLevel().toLowerCase());
        }
        if(filterDTO.getAge()!=null){
            stringBuilder.append("and lower(c.age)=:age");
            params.put("age", filterDTO.getAge());
        }
        if(filterDTO.getGender()!=null){
            stringBuilder.append("and lower(c.gender)=:gender");
            params.put("gender", filterDTO.getGender().toLowerCase());
        }
        if(filterDTO.getCreatedDateFrom()!=null){
            stringBuilder.append(" and c.createdDate>:createdDateFrom ");
            params.put("createdDateFrom",filterDTO.getCreatedDateFrom());
        }
        if(filterDTO.getCreatedDateTo()!=null){
            stringBuilder.append(" and c.createdDate>:createdDateTo ");
            params.put("createdDateTo",filterDTO.getCreatedDateTo());
        }

        Query query = entityManager.createQuery(stringBuilder.toString());
        //params
        for (Map.Entry<String, Object> param: params.entrySet()){
            query.setParameter(param.getKey(), param.getValue());
        }

        List<StudentEntity> entityList = query.getResultList();

        return entityList;
    }


}
