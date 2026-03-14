package com.school.management.studentfees.service;

import com.school.management.studentfees.dto.CollectFeeRequest;
import com.school.management.studentfees.dto.ReceiptResponse;
import com.school.management.studentfees.entity.Fee;
import com.school.management.studentfees.entity.Student;
import com.school.management.studentfees.repository.FeeRepository;
import com.school.management.studentfees.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeeServiceImplTest {

    @Mock
    private FeeRepository feeRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private FeeServiceImpl feeService;

    @Test
    void shouldCollectFeeSuccessfully() {
        CollectFeeRequest request = new CollectFeeRequest();
        request.setStudentId("ST1001");
        request.setAmountPaid(new BigDecimal("1500.00"));

        Student student = new Student();
        student.setStudentId("ST1001");
        student.setStudentName("Emma Wilson");
        student.setGrade("10");
        student.setSchoolName("Riverside High School");
        student.setMobileNumber("9876543210");

        when(studentRepository.findByStudentId("ST1001")).thenReturn(Optional.of(student));

        Fee savedFee = new Fee();
        savedFee.setReceiptNo("RCPT-TEST123");
        savedFee.setStudent(student);
        savedFee.setAmountPaid(new BigDecimal("1500.00"));
        savedFee.setPaymentDate(LocalDateTime.now());

        when(feeRepository.save(any(Fee.class))).thenReturn(savedFee);

        ReceiptResponse response = feeService.collectFee(request);

        assertNotNull(response);
        assertEquals("RCPT-TEST123", response.getReceiptNo());
        assertEquals("ST1001", response.getStudentId());
        assertEquals("Emma Wilson", response.getStudentName());
        assertEquals("10", response.getGrade());
        assertEquals("Riverside High School", response.getSchoolName());
        assertEquals(new BigDecimal("1500.00"), response.getAmountPaid());

        verify(studentRepository).findByStudentId("ST1001");
        verify(feeRepository).save(any(Fee.class));
    }

    @Test
    void shouldThrowExceptionWhenStudentNotFoundDuringFeeCollection() {
        CollectFeeRequest request = new CollectFeeRequest();
        request.setStudentId("ST9999");
        request.setAmountPaid(new BigDecimal("1500.00"));

        when(studentRepository.findByStudentId("ST9999")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> feeService.collectFee(request)
        );

        assertEquals("Student not found with id: ST9999", exception.getMessage());
        verify(studentRepository).findByStudentId("ST9999");
    }

    @Test
    void shouldGetReceiptSuccessfully() {
        Student student = new Student();
        student.setStudentId("ST1001");
        student.setStudentName("Emma Wilson");
        student.setGrade("10");
        student.setSchoolName("Riverside High School");
        student.setMobileNumber("9876543210");

        Fee fee = new Fee();
        fee.setReceiptNo("RCPT-TEST123");
        fee.setStudent(student);
        fee.setAmountPaid(new BigDecimal("1500.00"));
        fee.setPaymentDate(LocalDateTime.now());

        when(feeRepository.findByReceiptNo("RCPT-TEST123")).thenReturn(Optional.of(fee));

        ReceiptResponse response = feeService.getReceipt("RCPT-TEST123");

        assertNotNull(response);
        assertEquals("RCPT-TEST123", response.getReceiptNo());
        assertEquals("ST1001", response.getStudentId());
        assertEquals("Emma Wilson", response.getStudentName());
        assertEquals(new BigDecimal("1500.00"), response.getAmountPaid());

        verify(feeRepository).findByReceiptNo("RCPT-TEST123");
    }

    @Test
    void shouldThrowExceptionWhenReceiptNotFound() {
        when(feeRepository.findByReceiptNo("RCPT-UNKNOWN")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> feeService.getReceipt("RCPT-UNKNOWN")
        );

        assertEquals("Receipt not found: RCPT-UNKNOWN", exception.getMessage());
        verify(feeRepository).findByReceiptNo("RCPT-UNKNOWN");
    }
}
