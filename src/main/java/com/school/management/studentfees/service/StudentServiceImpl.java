package com.school.management.studentfees.service;

import com.school.management.studentfees.dto.AddStudentRequest;
import com.school.management.studentfees.dto.StudentResponse;
import com.school.management.studentfees.entity.Student;
import com.school.management.studentfees.exception.StudentExistsException;
import com.school.management.studentfees.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponse addStudent(AddStudentRequest request) {
        if (studentRepository.existsByStudentId(request.getStudentId())) {
            throw new StudentExistsException("Student already exists with id: " + request.getStudentId());
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
