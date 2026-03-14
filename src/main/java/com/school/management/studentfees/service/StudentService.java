package com.school.management.studentfees.service;

import com.school.management.studentfees.dto.StudentRequest;
import com.school.management.studentfees.dto.StudentResponse;

public interface StudentService {

    StudentResponse addStudent(StudentRequest request);
}
