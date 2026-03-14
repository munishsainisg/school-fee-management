package com.school.management.studentfees.service;

import com.school.management.studentfees.dto.CollectFeeRequest;
import com.school.management.studentfees.dto.ReceiptResponse;

public interface FeeService {

    ReceiptResponse collectFee(CollectFeeRequest request);

    ReceiptResponse getReceipt(String receiptNo);
}
