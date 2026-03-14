package com.school.management.studentfees.service;

import com.school.management.studentfees.dto.StudentRequest;
import com.school.management.studentfees.dto.StudentResponse;
import com.school.management.studentfees.entity.Student;
import com.school.management.studentfees.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponse addStudent(StudentRequest request) {
        if (studentRepository.existsByStudentId(request.getStudentId())) {
            throw new RuntimeException("Student already exists with id: " + request.getStudentId());
        }

        Student student = new Student();
        student.setStudentId(request.getStudentId());
        student.setStudentName(request.getStudentName());
        student.setGrade(request.getGrade());
        student.setMobileNumber(request.getMobileNumber());
        student.setSchoolName(request.getSchoolName());

        Student savedStudent = studentRepository.save(student);

        return new StudentResponse(
                savedStudent.getStudentId(),
                savedStudent.getStudentName(),
                savedStudent.getGrade(),
                savedStudent.getMobileNumber(),
                savedStudent.getSchoolName()
        );
    }
}
