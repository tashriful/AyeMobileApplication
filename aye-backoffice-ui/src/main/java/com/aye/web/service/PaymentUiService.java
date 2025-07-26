package com.aye.web.service;


import com.aye.web.model.payment.PaymentStatusDto;
import com.aye.web.model.payment.PaymentsMDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaymentUiService {
    List<PaymentsMDto> getAllPostedPayment(String accessToken, PaymentStatusDto paymentStatusDto);

    PaymentsMDto findPaymentById(String paymentId, String accessToken);

    String approvePayment(String paymentId, String accessToken);

    ResponseEntity<byte[]> downloadPaymentAttachment(String id, String accessToken);
}
