package com.aye.web.repo;

import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.model.schedule.OrdDelvScheduleHeaderM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdDelvSchdHeaderMRepo extends MongoRepository<OrdDelvScheduleHeaderM, String> {
    OrdDelvScheduleHeaderM findTopByOrgHierarchyOrderByScheduleNumberDesc(OrgHierarchyM orgHierarchyM);
}
