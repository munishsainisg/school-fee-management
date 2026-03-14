package com.school.management.studentfees.controller;

import com.school.management.studentfees.dto.CollectFeeRequest;
import com.school.management.studentfees.dto.ReceiptResponse;
import com.school.management.studentfees.service.FeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Collect student fee", description = "Collects fee for an existing student and generates a receipt")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Fee collected successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ReceiptResponse> collectFee(@Valid @RequestBody CollectFeeRequest request) {
        ReceiptResponse response = feeService.collectFee(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/receipt/{receiptNo}")
    @Operation(summary = "Get receipt by receipt number", description = "Fetches receipt details for a given receipt number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receipt fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Receipt not found")
    })
    public ResponseEntity<ReceiptResponse> getReceipt(@PathVariable String receiptNo) {
        ReceiptResponse response = feeService.getReceipt(receiptNo);
        return ResponseEntity.ok(response);
    }
}
