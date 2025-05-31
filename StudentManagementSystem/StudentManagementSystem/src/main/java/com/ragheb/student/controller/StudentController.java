package com.ragheb.student.controller;

import com.ragheb.student.entity.Student;
import com.ragheb.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    //@ResponseBody -> return JSON
    public String getAllStudents(Model model,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "5") int size,
                                 @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Student> pageStudents = studentService.findByFirstNameContaining(keyword, PageRequest.of(page, size));
        model.addAttribute("students", pageStudents.getContent());
        model.addAttribute("pages", new int[pageStudents.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        Student student = new Student(); // to hold the student object
        model.addAttribute("student", student);
        return "create-student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Integer id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit-student";
    }

    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable Integer id, @ModelAttribute("student") Student student) {
        Student existingStudent = studentService.getStudentById(id);
        //existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        studentService.saveStudent(existingStudent);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Integer id, String keyword, int page) {
        studentService.deleteStudentById(id);
        return "redirect:/students?page=" + page + "&keyword=" + keyword;
    }
}
