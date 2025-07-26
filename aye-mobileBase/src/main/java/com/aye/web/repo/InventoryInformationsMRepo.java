package com.aye.web.repo;

import com.aye.web.model.common.InventoryInformationsM;
import com.aye.web.model.common.OrgHierarchyM;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InventoryInformationsMRepo extends MongoRepository<InventoryInformationsM, String> {

    List<InventoryInformationsM> findAllByOrgHierarchy(OrgHierarchyM orgHierarchyM);

}
