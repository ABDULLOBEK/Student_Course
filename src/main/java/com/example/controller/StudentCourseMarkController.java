package com.example.controller;


import com.example.dto.StudentCourseMarkDTO;
import com.example.mapper.DTOMapper;
import com.example.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/mark")
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService studentCourseMarkService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseMarkDTO scmDto) {
        StudentCourseMarkDTO response = studentCourseMarkService.add(scmDto);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@RequestBody StudentCourseMarkDTO scmDto,
                                 @PathVariable("id") Integer id) {
        studentCourseMarkService.update(id, scmDto);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getById(id));
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<DTOMapper> getByIdDTO(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getByIdDTO(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        int response = studentCourseMarkService.delete(id);
        if (response > 0) {
            return ResponseEntity.ok("Student Deleted");
        }
        return ResponseEntity.badRequest().body("Student not found");
    }

    @GetMapping("/all")
    public List<StudentCourseMarkDTO> all() {
        return studentCourseMarkService.getAll();
    }

    @GetMapping("/date")
    public ResponseEntity<?> getMarkByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateI,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateF) {
        return ResponseEntity.ok(studentCourseMarkService.getMarkByDate(dateI, dateF));
    }

    @GetMapping("/twoDay")
    public ResponseEntity<?> getMarkByTwoDay(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateI,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateF) {
        return ResponseEntity.ok(studentCourseMarkService.getMarkByTwoDay(dateI, dateF));
    }

    @GetMapping("/allMark")
    public ResponseEntity<List<Integer>> allMark() {
        return ResponseEntity.ok(studentCourseMarkService.getAllMark());
    }

    @GetMapping("/markCourse")
    public ResponseEntity<List<Integer>> markListByCourse(@RequestParam("course_id") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.markListByCourse(course_id));
    }

    @GetMapping("/lastMark")
    public ResponseEntity<Integer> lastMark() {
        return ResponseEntity.ok(studentCourseMarkService.lastMark());
    }

    @GetMapping("/bigThree")
    public ResponseEntity<List<Integer>> bigThreeMark() {
        return ResponseEntity.ok(studentCourseMarkService.bigThreeMark());
    }

    @GetMapping("/firstMark")
    public ResponseEntity<Integer> firstMark() {
        return ResponseEntity.ok(studentCourseMarkService.firstMark());
    }

    @GetMapping("/firstMarkCourse")
    public ResponseEntity<Integer> firstMarkByCourse(@RequestParam("course_id") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.firstMarkByCourse(course_id));
    }

    @GetMapping("/highestMark")
    public ResponseEntity<Integer> highestMarkByCourse(@RequestParam("course_id") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.highestMarkByCourse(course_id));
    }

    @GetMapping("/avgMark")
    public ResponseEntity<Double> avgMark() {
        return ResponseEntity.ok(studentCourseMarkService.avgMark());
    }

    @GetMapping("/avbByCourse")
    public ResponseEntity<Double> avbByCourse(@RequestParam("course_id") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.avbByCourse(course_id));
    }

    @GetMapping("/maxMarkCountByCourse")
    public ResponseEntity<Integer> maxMarkCountByCourse(@RequestParam("course_id") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.maxMarkCountByCourse(course_id));
    }

    @GetMapping("/maxMarkByCourse")
    public ResponseEntity<Integer> maxMarkByCourse(@RequestParam("course_id") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.maxMarkByCourse(course_id));
    }

    @GetMapping("/avgMarkByCourse")
    public ResponseEntity<Double> avgMarkByCourse(@RequestParam("course_id") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.avgMarkByCourse(course_id));
    }

    @GetMapping("/countMarkByCourse")
    public ResponseEntity<Integer> countMarkByCourse(@RequestParam("course_id") Integer course_id) {
        return ResponseEntity.ok(studentCourseMarkService.countMarkByCourse(course_id));
    }

    @GetMapping(value = "/pagination")
    public ResponseEntity<?> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {

        return ResponseEntity.ok(studentCourseMarkService.studentCourseMarkPagination(page - 1, size));
    }

    @GetMapping(value = "/paginationByStudentID")
    public ResponseEntity<?> findAllByStudentOrderByCreatedDate(@RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                               @RequestParam("student_id") int student_id) {
        return ResponseEntity.ok(studentCourseMarkService.findAllByStudentOrderByCreatedDate(student_id,page - 1, size));
    }

    @GetMapping(value = "/paginationByCourseID")
    public ResponseEntity<?> findAllByCourseOrderByCreatedDate(@RequestParam(value = "page", defaultValue = "1") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size,
                                                @RequestParam("course_id") int course_id) {
        return ResponseEntity.ok(studentCourseMarkService.findAllByCourseOrderByCreatedDate(course_id,page - 1, size));
    }



}
