package com.school.management.studentfees.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.management.studentfees.dto.StudentRequest;
import com.school.management.studentfees.dto.StudentResponse;
import com.school.management.studentfees.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private StudentService studentService;

    @Test
    @DisplayName("should create student successfully")
    void should_create_student_successfully() throws Exception {
        StudentRequest request = new StudentRequest();
        request.setStudentId("STU1001");
        request.setStudentName("Aarav Sharma");
        request.setGrade("10");
        request.setMobileNumber("9876543210");
        request.setSchoolName("RAK International School");

        StudentResponse response = new StudentResponse();
        response.setStudentId("STU1001");
        response.setStudentName("Aarav Sharma");
        response.setGrade("10");
        response.setMobileNumber("9876543210");
        response.setSchoolName("RAK International School");

        when(studentService.addStudent(any(StudentRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.studentId").value("STU1001"))
                .andExpect(jsonPath("$.studentName").value("Aarav Sharma"))
                .andExpect(jsonPath("$.grade").value("10"))
                .andExpect(jsonPath("$.mobileNumber").value("9876543210"))
                .andExpect(jsonPath("$.schoolName").value("RAK International School"));
    }

    @Test
    @DisplayName("should return bad request when mandatory fields are missing")
    void should_return_bad_request_when_request_is_invalid() throws Exception {
        StudentRequest request = new StudentRequest();
        request.setStudentId("");
        request.setStudentName("");
        request.setGrade("");
        request.setMobileNumber("");
        request.setSchoolName("");

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
