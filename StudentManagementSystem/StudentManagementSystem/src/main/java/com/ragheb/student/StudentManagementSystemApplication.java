package com.ragheb.student;

import com.ragheb.student.entity.Student;
import com.ragheb.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            if (studentRepository.count() == 0) {
                studentRepository.save(new Student(null, "Hassan", "mohamed", "hassan@gmail.com"));
                studentRepository.save(new Student(null, "Ahmed", "sheta", "ahmed@gmail.com"));
                studentRepository.save(new Student(null, "Mohamed", "Ali", "mohamed@gmail.com"));
                studentRepository.save(new Student(null, "Ali", "Mohamed", "ali@gmail.com"));
                studentRepository.save(new Student(null, "Mohamed", "Ahmed", "mohamed@gmail.com"));
            }

			studentRepository.findAll().forEach(student -> {
				System.out.println(student.getFirstName());
			});
        };
    }
}
