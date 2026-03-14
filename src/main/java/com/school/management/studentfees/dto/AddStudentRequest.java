package com.school.management.studentfees.dto;

import jakarta.validation.constraints.NotBlank;

public class AddStudentRequest {

    @NotBlank(message = "Student id is required")
    private String studentId;

    @NotBlank(message = "Student name is required")
    private String studentName;

    @NotBlank(message = "Grade is required")
    private String grade;

    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

    @NotBlank(message = "School name is required")
    private String schoolName;

    public AddStudentRequest() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
