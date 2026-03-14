package com.school.management.studentfees.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class StudentRequest {

    @Schema(description = "Unique student identifier", example = "ST1001")
    @NotBlank(message = "Student id is required")
    private String studentId;

    @Schema(description = "Full name of the student", example = "David Anderson")
    @NotBlank(message = "Student name is required")
    private String studentName;

    @Schema(description = "Student grade or class", example = "10")
    @NotBlank(message = "Grade is required")
    private String grade;

    @Schema(description = "Mobile number of the student", example = "9876543210")
    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

    @Schema(description = "Name of the school", example = "Hillcrest School")
    @NotBlank(message = "School name is required")
    private String schoolName;

    public StudentRequest() {
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
