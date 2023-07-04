package com.example.controller;

import com.example.dto.CourseDTO;
import com.example.dto.StudentDTO;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
  /*id,name,price,duration,createdDate
  1. Create Course
  2. Get Course by id.
  3. Get Course list. return all.
  4. Update Course.
  5. Delete Course
  6. Get method by each field. (getByName, getByPrice,getByDuration)
  7. Get Course list between given 2 prices.
  8. Get Course list between 2 createdDates*/

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CourseDTO course){
        CourseDTO response = courseService.add(course);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(courseService.getById(id));
    }

    @GetMapping("/all")
    public List<CourseDTO> all(){
        return courseService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@RequestBody CourseDTO course,
                                 @PathVariable("id") Integer id){
        courseService.update(id, course);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Boolean response = courseService.delete(id);
        if(response){
            return ResponseEntity.ok("Course Deleted");
        }
        return ResponseEntity.badRequest().body("Course not found");
    }

    @GetMapping("/name")
    public ResponseEntity<?> getByName(@RequestParam("name") String name){
        return ResponseEntity.ok(courseService.getByName(name));
    }

    @GetMapping("/price")
    public ResponseEntity<?> getByPrice(@RequestParam("price") Double price){
        return ResponseEntity.ok(courseService.getByPrice(price));
    }

    @GetMapping("/duration")
    public ResponseEntity<?> getByDuration(@RequestParam("duration") String duration){
        return ResponseEntity.ok(courseService.getByDuration(duration));
    }


}
