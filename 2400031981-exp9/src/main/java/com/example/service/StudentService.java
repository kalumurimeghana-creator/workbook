package com.example.service;
import com.example.entity.Student;

public interface StudentService {

   
    Student saveStudent(Student student);
    Student getStudentById(int id);
}