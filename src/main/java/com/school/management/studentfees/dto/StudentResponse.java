package com.school.management.studentfees.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class StudentResponse {

    @Schema(description = "Student identifier", example = "ST1001")
    private String studentId;

    @Schema(description = "Student full name", example = "David Anderson")
    private String studentName;

    @Schema(description = "Student grade", example = "10")
    private String grade;

    @Schema(description = "Mobile number", example = "9876543210")
    private String mobileNumber;

    @Schema(description = "School name", example = "Hillcrest School")
    private String schoolName;

    public StudentResponse() {
    }

    public StudentResponse(String studentId, String studentName, String grade, String mobileNumber, String schoolName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.grade = grade;
        this.mobileNumber = mobileNumber;
        this.schoolName = schoolName;
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
