package com.school.management.studentfees.service;

import com.school.management.studentfees.dto.AddStudentRequest;
import com.school.management.studentfees.dto.StudentResponse;

public interface StudentService {

    StudentResponse addStudent(AddStudentRequest request);
}
