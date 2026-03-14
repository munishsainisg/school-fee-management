package com.school.management.studentfees.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReceiptResponse {

    @Schema(description = "Generated receipt number", example = "RCPT-AB12CD34")
    private String receiptNo;

    @Schema(description = "Student identifier", example = "ST1001")
    private String studentId;

    @Schema(description = "Student name", example = "Rahul Sharma")
    private String studentName;

    @Schema(description = "Student grade", example = "10")
    private String grade;

    @Schema(description = "School name", example = "Green Valley School")
    private String schoolName;

    @Schema(description = "Fee amount paid", example = "1500.00")
    private BigDecimal amountPaid;

    @Schema(description = "Payment date and time", example = "2026-03-14T10:15:30")
    private LocalDateTime paymentDate;

    public ReceiptResponse() {
    }

    public ReceiptResponse(String receiptNo,
                           String studentId,
                           String studentName,
                           String grade,
                           String schoolName,
                           BigDecimal amountPaid,
                           LocalDateTime paymentDate) {
        this.receiptNo = receiptNo;
        this.studentId = studentId;
        this.studentName = studentName;
        this.grade = grade;
        this.schoolName = schoolName;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
