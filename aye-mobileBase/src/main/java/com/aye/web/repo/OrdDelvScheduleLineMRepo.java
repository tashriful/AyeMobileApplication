package com.aye.web.repo;

import com.aye.web.model.schedule.OrdDelvScheduleLineM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdDelvScheduleLineMRepo extends MongoRepository<OrdDelvScheduleLineM, String> {
}
