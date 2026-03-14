package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.model.Course;
import com.example.service.CourseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    // CREATE
    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        if (course.getTitle() == null || course.getTitle().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Title cannot be empty");
        }

        Course saved = service.addCourse(course);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(service.getAllCourses());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {

        Optional<Course> course = service.getCourseById(id);

        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Course not found");
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id,
                                          @RequestBody Course course) {

        Course updated = service.updateCourse(id, course);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Course not found. Update failed.");
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {

        boolean deleted = service.deleteCourse(id);

        if (deleted) {
            return ResponseEntity.ok("Course deleted successfully");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Course not found");
        }
    }

    // SEARCH
    @GetMapping("/search/{title}")
    public ResponseEntity<List<Course>> searchCourse(@PathVariable String title) {

        List<Course> courses = service.searchByTitle(title);

        if (courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(courses);
        }

        return ResponseEntity.ok(courses);
    }
}