package com.aye.web.repo;

import com.aye.web.model.order.DeliveryLineM;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryLineMRepo extends MongoRepository<DeliveryLineM, Long> {
}
