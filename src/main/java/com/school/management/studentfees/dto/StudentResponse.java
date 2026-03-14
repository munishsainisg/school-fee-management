package com.school.management.studentfees.dto;

public class StudentResponse {

    private String studentId;
    private String studentName;
    private String grade;
    private String mobileNumber;
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
