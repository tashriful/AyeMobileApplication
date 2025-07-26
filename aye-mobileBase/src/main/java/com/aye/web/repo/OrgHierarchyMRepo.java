package com.aye.web.repo;

import com.aye.web.model.common.OrgHierarchyM;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrgHierarchyMRepo extends MongoRepository<OrgHierarchyM, String> {
}
