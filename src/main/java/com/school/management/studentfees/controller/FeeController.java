package com.school.management.studentfees.controller;

import com.school.management.studentfees.dto.CollectFeeRequest;
import com.school.management.studentfees.dto.ReceiptResponse;
import com.school.management.studentfees.service.FeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fees")
public class FeeController {

    private final FeeService feeService;

    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    @PostMapping("/collect")
    public ResponseEntity<ReceiptResponse> collectFee(@Valid @RequestBody CollectFeeRequest request) {
        ReceiptResponse response = feeService.collectFee(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/receipt/{receiptNo}")
    public ResponseEntity<ReceiptResponse> getReceipt(@PathVariable String receiptNo) {
        ReceiptResponse response = feeService.getReceipt(receiptNo);
        return ResponseEntity.ok(response);
    }
}
