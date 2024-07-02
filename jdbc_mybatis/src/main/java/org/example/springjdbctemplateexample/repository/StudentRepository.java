package org.example.springjdbctemplateexample.repository;

import org.example.springjdbctemplateexample.domain.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();
    int insertStudent(Student student);

}
