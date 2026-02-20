package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Course;
import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {

    List<Course> findByTitleContainingIgnoreCase(String title);
}