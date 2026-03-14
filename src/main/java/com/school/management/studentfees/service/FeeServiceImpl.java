package com.school.management.studentfees.service;

import com.school.management.studentfees.dto.CollectFeeRequest;
import com.school.management.studentfees.dto.ReceiptResponse;
import com.school.management.studentfees.entity.Fee;
import com.school.management.studentfees.entity.Student;
import com.school.management.studentfees.repository.FeeRepository;
import com.school.management.studentfees.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepository;
    private final StudentRepository studentRepository;

    public FeeServiceImpl(FeeRepository feeRepository, StudentRepository studentRepository) {
        this.feeRepository = feeRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public ReceiptResponse collectFee(CollectFeeRequest request) {
        Student student = studentRepository.findByStudentId(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + request.getStudentId()));

        Fee fee = new Fee();
        fee.setReceiptNo(generateReceiptNo());
        fee.setStudent(student);
        fee.setAmountPaid(request.getAmountPaid());
        fee.setPaymentDate(LocalDateTime.now());

        Fee savedFee = feeRepository.save(fee);

        return buildReceiptResponse(savedFee);
    }

    @Override
    public ReceiptResponse getReceipt(String receiptNo) {
        Fee fee = feeRepository.findByReceiptNo(receiptNo)
                .orElseThrow(() -> new RuntimeException("Receipt not found: " + receiptNo));

        return buildReceiptResponse(fee);
    }

    private ReceiptResponse buildReceiptResponse(Fee fee) {
        Student student = fee.getStudent();

        return new ReceiptResponse(
                fee.getReceiptNo(),
                student.getStudentId(),
                student.getStudentName(),
                student.getGrade(),
                student.getSchoolName(),
                fee.getAmountPaid(),
                fee.getPaymentDate()
        );
    }

    private String generateReceiptNo() {
        return "RCPT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
