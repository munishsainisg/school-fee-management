package com.school.management.studentfees.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.management.studentfees.dto.CollectFeeRequest;
import com.school.management.studentfees.dto.ReceiptResponse;
import com.school.management.studentfees.service.FeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FeeController.class)
class FeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FeeService feeService;

    @Test
    @DisplayName("should collect fee successfully")
    void should_collect_fee_successfully() throws Exception {
        CollectFeeRequest request = new CollectFeeRequest();
        request.setStudentId("STU1001");
        request.setAmountPaid(new BigDecimal("1500.00"));

        ReceiptResponse response = new ReceiptResponse();
        response.setReceiptNo("RCPT-10001");
        response.setStudentId("STU1001");
        response.setStudentName("Aarav Sharma");
        response.setGrade("10");
        response.setSchoolName("RAK International School");
        response.setAmountPaid(new BigDecimal("1500.00"));
        response.setPaymentDate(LocalDateTime.of(2026, 3, 13, 10, 30));

        when(feeService.collectFee(any(CollectFeeRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/fees/collect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.receiptNo").value("RCPT-10001"))
                .andExpect(jsonPath("$.studentId").value("STU1001"))
                .andExpect(jsonPath("$.studentName").value("Aarav Sharma"))
                .andExpect(jsonPath("$.amountPaid").value(1500.00));
    }

    @Test
    @DisplayName("should return bad request when fee collection request is invalid")
    void should_return_bad_request_when_fee_request_is_invalid() throws Exception {
        CollectFeeRequest request = new CollectFeeRequest();
        request.setStudentId("");
        request.setAmountPaid(null);

        mockMvc.perform(post("/api/fees/collect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should return receipt by receipt number")
    void should_return_receipt_by_receipt_number() throws Exception {
        ReceiptResponse response = new ReceiptResponse();
        response.setReceiptNo("RCPT-10001");
        response.setStudentId("STU1001");
        response.setStudentName("Aarav Sharma");
        response.setGrade("10");
        response.setSchoolName("RAK International School");
        response.setAmountPaid(new BigDecimal("1500.00"));
        response.setPaymentDate(LocalDateTime.of(2026, 3, 13, 10, 30));

        when(feeService.getReceipt(eq("RCPT-10001"))).thenReturn(response);

        mockMvc.perform(get("/api/fees/receipt/RCPT-10001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.receiptNo").value("RCPT-10001"))
                .andExpect(jsonPath("$.studentId").value("STU1001"))
                .andExpect(jsonPath("$.amountPaid").value(1500.00));
    }
}
