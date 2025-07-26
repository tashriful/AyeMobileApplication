package com.aye.web.repo;

import com.aye.web.model.ar.PaymentStatus;
import com.aye.web.model.ar.PaymentsM;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentsMRepo extends MongoRepository<PaymentsM, String> {
    PaymentsM findTopByOrderByNumberDesc();
    List<PaymentsM> findAllByCustomerLineM();
    List<PaymentsM> findAllByPaymentStatus(PaymentStatus paymentStatus);
}
