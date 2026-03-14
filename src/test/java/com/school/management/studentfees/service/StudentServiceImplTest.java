package com.school.management.studentfees.service;

import com.school.management.studentfees.dto.StudentRequest;
import com.school.management.studentfees.dto.StudentResponse;
import com.school.management.studentfees.entity.Student;
import com.school.management.studentfees.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void shouldAddStudentSuccessfully() {
        StudentRequest request = new StudentRequest();
        request.setStudentId("ST1001");
        request.setStudentName("Emma Wilson");
        request.setGrade("10");
        request.setMobileNumber("9876543210");
        request.setSchoolName("Riverside High School");

        when(studentRepository.existsByStudentId("ST1001")).thenReturn(false);

        Student savedStudent = new Student();
        savedStudent.setStudentId("ST1001");
        savedStudent.setStudentName("Emma Wilson");
        savedStudent.setGrade("10");
        savedStudent.setMobileNumber("9876543210");
        savedStudent.setSchoolName("Riverside High School");

        when(studentRepository.save(any(Student.class))).thenReturn(savedStudent);

        StudentResponse response = studentService.addStudent(request);

        assertNotNull(response);
        assertEquals("ST1001", response.getStudentId());
        assertEquals("Emma Wilson", response.getStudentName());
        assertEquals("10", response.getGrade());
        assertEquals("9876543210", response.getMobileNumber());
        assertEquals("Riverside High School", response.getSchoolName());

        verify(studentRepository).existsByStudentId("ST1001");
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void shouldThrowExceptionWhenStudentAlreadyExists() {
        StudentRequest request = new StudentRequest();
        request.setStudentId("ST1001");
        request.setStudentName("Emma Wilson");
        request.setGrade("10");
        request.setMobileNumber("9876543210");
        request.setSchoolName("Riverside High School");

        when(studentRepository.existsByStudentId("ST1001")).thenReturn(true);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> studentService.addStudent(request)
        );

        assertEquals("Student already exists with id: ST1001", exception.getMessage());

        verify(studentRepository).existsByStudentId("ST1001");
        verify(studentRepository, never()).save(any(Student.class));
    }
}
