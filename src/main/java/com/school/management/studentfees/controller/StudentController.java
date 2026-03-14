package com.school.management.studentfees.controller;

import com.school.management.studentfees.dto.StudentRequest;
import com.school.management.studentfees.dto.StudentResponse;
import com.school.management.studentfees.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Add a new student", description = "Creates a new student record in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "409", description = "Student already exists")
    })
    public ResponseEntity<StudentResponse> addStudent(@Valid @RequestBody StudentRequest request) {
        StudentResponse response = studentService.addStudent(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
