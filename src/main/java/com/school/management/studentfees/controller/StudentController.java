package com.school.management.studentfees.controller;

import com.school.management.studentfees.dto.AddStudentRequest;
import com.school.management.studentfees.dto.StudentResponse;
import com.school.management.studentfees.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(@Valid @RequestBody AddStudentRequest request) {
        StudentResponse response = studentService.addStudent(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
