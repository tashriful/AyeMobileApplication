package com.aye.web.repo;

import com.aye.web.model.order.OrderLineM;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderLineMRepo extends MongoRepository<OrderLineM, String> {

    List<OrderLineM> findAllByOrderHeaderId(String headerId);
}
