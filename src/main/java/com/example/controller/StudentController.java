package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
/*1. Create student D/D
  2. Get Student List. return all. D/D
  3. Get student by id. D/D
  4. Update student. D/D
  5. Delete Student by id. D/D
  6. Get method by each field. (getByName, getBySurname, getByLevel, getByAge, getByGender) D/D
  7. Get StudentList by Given Date.
  8. Get StudentList  between given dates.*/

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO student){
        StudentDTO response = studentService.add(student);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/all")
    public List<StudentDTO> all(){
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(studentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@RequestBody StudentDTO student,
                                 @PathVariable("id") Integer id){
        studentService.update(id, student);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Boolean response = studentService.delete(id);
        if(response){
            return ResponseEntity.ok("Student Deleted");
        }
        return ResponseEntity.badRequest().body("Student not found");
    }

    @GetMapping("/name")
    public ResponseEntity<?> getByName(@RequestParam("name") String name){
        return ResponseEntity.ok(studentService.getByName(name));
    }

    @GetMapping("/surname")
    public ResponseEntity<?> getBySurname(@RequestParam("surname") String surname){
        return ResponseEntity.ok(studentService.getBySurname(surname));
    }

    @GetMapping("/level")
    public ResponseEntity<?> getByLevel(@RequestParam("level") String level){
        return ResponseEntity.ok(studentService.getByLevel(level));
    }

    @GetMapping("/age")
    public ResponseEntity<?> getByAge(@RequestParam("age") Integer age){
        return ResponseEntity.ok(studentService.getByAge(age));
    }

    @GetMapping("/gender")
    public ResponseEntity<?> getByGender(@RequestParam("gender") String gender){
        return ResponseEntity.ok(studentService.getByGender(gender));
    }

    @GetMapping("/date/")
    public ResponseEntity<?> getByDate(@RequestParam("date")
                                               String  date){

        return ResponseEntity.ok(studentService.getByDate(date));
    }

    @GetMapping("/dates")
    public ResponseEntity<?> getByBetweenDate(@RequestParam String dateI,
                                              @RequestParam String dateF){
        return ResponseEntity.ok(studentService.getByBetweenDate(dateI,dateF));
    }

}
