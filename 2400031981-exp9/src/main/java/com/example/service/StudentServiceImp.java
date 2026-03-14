package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.repo.StudentRepository;
import com.example.exception.StudentNotFoundException;
import com.example.exception.InvalidInputException;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentRepository repo;

    @Override
    public Student getStudentById(int id) {

        if(id <= 0) {
            throw new InvalidInputException("Invalid Student ID");
        }

        return repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));
    }
    @Override
    public Student saveStudent(Student student) {
        return repo.save(student);
    }
}