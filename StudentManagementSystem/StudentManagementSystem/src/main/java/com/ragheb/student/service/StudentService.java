package com.ragheb.student.service;

import com.ragheb.student.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudents();
    public Student saveStudent(Student student);
    public Student getStudentById(Integer id);
    public void deleteStudentById(Integer id);
    public Page<Student> findByFirstNameContaining(String keyword, Pageable pageable);
}
