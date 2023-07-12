package com.example.repository;

import com.example.dto.StudentCourseMarkFilterDTO;
import com.example.dto.StudentFilterDTO;
import com.example.entity.StudentCourseMarkEntity;
import com.example.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomStudentCourseMarkRepository {
    @Autowired
    private EntityManager entityManager;

    public List<StudentCourseMarkEntity> filter(StudentCourseMarkFilterDTO filterDTO){
        StringBuilder stringBuilder = new StringBuilder("select c from StudentCourseMarkEntity as c where 1=1 ");
        Map<String, Object> params = new HashMap<>();
        if(filterDTO.getStudentId()!=null){
            stringBuilder.append(" and c.studentId =:studentId ");
            params.put("studentId",filterDTO.getStudentId());
        }
        if(filterDTO.getCourseId()!=null){
            stringBuilder.append(" and c.courseId =:courseId ");
            params.put("courseId",filterDTO.getCourseId());
        }
        if(filterDTO.getMark()!=null){
            stringBuilder.append(" and c.mark =:mark ");
            params.put("mark",filterDTO.getMark());
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

        List<StudentCourseMarkEntity> entityList = query.getResultList();

        return entityList;
    }

}
