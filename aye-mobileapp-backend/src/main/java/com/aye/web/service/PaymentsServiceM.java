package com.aye.web.service;



import com.aye.web.exception.GeneralExceptions;
import com.aye.web.model.ar.PaymentStatus;
import com.aye.web.model.ar.PaymentsM;
import com.aye.web.model.ar.PaymentsMSearchDto;
import com.aye.web.model.order.OrderHeaderM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface PaymentsServiceM {
    PaymentsM savePayment(PaymentsM paymentsM) throws GeneralExceptions, IOException;
    List<PaymentsM> getAllPayments();
    PaymentsM findPaymentsById(String id) throws IOException;

    PaymentsM updatePayments(PaymentsM paymentsM);

    Page<PaymentsM> searchPayment(PaymentsMSearchDto paymentsMSearchDto, Pageable pageable);

    void postPayment(String paymentId) throws GeneralExceptions;


    List<PaymentsM> findPaymentByStatus(PaymentStatus paymentStatus);
}
