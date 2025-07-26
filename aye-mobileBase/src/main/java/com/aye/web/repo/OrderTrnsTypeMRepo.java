package com.aye.web.repo;

import com.aye.web.model.common.InventoryInformationsM;
import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.model.order.OrderTrnsTypeM;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderTrnsTypeMRepo extends MongoRepository<OrderTrnsTypeM, String> {

    List<OrderTrnsTypeM> findAllByOrgHierarchyAndInvOrg(OrgHierarchyM orgHierarchyM, InventoryInformationsM inventoryInformationsM);
}
