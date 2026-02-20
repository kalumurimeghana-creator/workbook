package com.example.service;

import com.example.model.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course addCourse(Course course);

    List<Course> getAllCourses();

    Optional<Course> getCourseById(Long id);

    Course updateCourse(Long id, Course course);

    boolean deleteCourse(Long id);

    List<Course> searchByTitle(String title);
}