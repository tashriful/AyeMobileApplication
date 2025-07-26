package com.aye.web.repo;

import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.model.order.OrderHeaderM;
import com.aye.web.model.order.OrderStatusM;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderHeaderMRepo extends MongoRepository<OrderHeaderM, String> {

    OrderHeaderM findTopByOrgHierarchyOrderByOrderNumberDesc(OrgHierarchyM orgHierarchyM);
    List<OrderHeaderM> findAllByOrderStatus(OrderStatusM orderStatusM);
    
}
