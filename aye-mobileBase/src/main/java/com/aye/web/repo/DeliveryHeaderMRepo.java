package com.aye.web.repo;

import com.aye.web.model.order.DeliveryHeaderM;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryHeaderMRepo extends MongoRepository<DeliveryHeaderM, Long> {
}
