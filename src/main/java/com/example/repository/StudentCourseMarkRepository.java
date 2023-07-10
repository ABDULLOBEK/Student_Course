package com.example.repository;

import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseMarkEntity;
import com.example.entity.StudentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity, Integer>, PagingAndSortingRepository<StudentCourseMarkEntity, Integer> {

    //2
    @Transactional
    @Modifying
    @Query("update StudentCourseMarkEntity as s set s.mark =:mark where s.id  =:id ")
    int updateMark(@Param("id") Integer id, @Param("mark") Integer mark);

    //3,4
    @Query("from StudentCourseMarkEntity where id=:id")
    Optional<StudentCourseMarkEntity> findById(@Param("id") Integer id);

    //5
    @Transactional
    @Modifying
    @Query("delete from StudentCourseMarkEntity as s where s.id=:id ")
    int delete(@Param("id") Integer id);

    //6
    @Query("from StudentCourseMarkEntity ")//select * from student_course_mark
    List<StudentCourseMarkEntity> findAll();

    //7,8
    @Query("SELECT s.mark FROM StudentCourseMarkEntity s WHERE s.createdDate > :startDateTime AND s.createdDate <= :endDateTime")
    List<Integer> findByCreatedDateBetween(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

    //9
    @Query("select s.mark from StudentCourseMarkEntity as s order by s.createdDate desc ")
    List<Integer> markListDesc();

    //10
    @Query("select s.mark from StudentCourseMarkEntity as s where s.course=:course order by s.createdDate")
    List<Integer> markListByCourse(@Param("course") Integer course_id);

    //11
    @Query("select s.mark from StudentCourseMarkEntity as s order by s.createdDate desc limit 1")
    Integer lastMark();

    //12
    @Query("select s.mark from StudentCourseMarkEntity as s order by s.mark desc limit 3")
    List<Integer> bigThreeMark();

    //13
    @Query("select s.mark from StudentCourseMarkEntity as s order by s.createdDate asc limit 1")
    Integer firstMark();

    //14
    @Query("select s.mark from StudentCourseMarkEntity as s where s.course=:course order by s.createdDate asc limit 1")
    Integer firstMarkByCourse(@Param("course") Integer course_id);

    //15
    @Query("select s.mark from StudentCourseMarkEntity as s where s.course=:course order by s.course desc limit 1")
    Integer highestMarkByCourse(@Param("course") Integer course_id);

    //16
    @Query("select avg (s.mark) from StudentCourseMarkEntity as s")
    Double avgMark();

    //17
    @Query("select avg (s.mark) from StudentCourseMarkEntity as s where s.course=:course")
    Integer avbByCourse(@Param("course") Integer course_id);

    //18
    @Query("select count(s.mark) from StudentCourseMarkEntity as s where s.mark=(select max (sc.mark) from StudentCourseMarkEntity as sc where sc.course=:course)")
    Integer maxMarkListByCourse(@Param("course") Integer course_id);

    //19
    @Query("select s.mark from StudentCourseMarkEntity as s where s.course=:course order by s.course desc limit 1")
    Integer maxMarkByCourse(@Param("course") Integer course_id);

    //20
    @Query("select avg (s.mark) from StudentCourseMarkEntity as s where s.course=:course")
    Double avgMarkByCourse(@Param("course") Integer course_id);

    //21
    @Query("select count (s.mark) from StudentCourseMarkEntity as s where s.course=:course")
    Integer countMarkByCourse(@Param("course") Integer course_id);

    //23
    Page<StudentCourseMarkEntity> findAllByStudentOrderByCreatedDate(Integer student_id, Pageable pageable);

    //24
    Page<StudentCourseMarkEntity> findAllByCourseOrderByCreatedDate(Integer course_id, Pageable pageable);





}
