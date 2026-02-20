package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Course;
import com.example.repo.CourseRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepo repository;

    @Override
    public Course addCourse(Course course) {
        return repository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Course updateCourse(Long id, Course courseDetails) {
        Optional<Course> optionalCourse = repository.findById(id);

        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.setTitle(courseDetails.getTitle());
            course.setDuration(courseDetails.getDuration());
            course.setFee(courseDetails.getFee());
            return repository.save(course);
        }
        return null;
    }

    @Override
    public boolean deleteCourse(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Course> searchByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }
}