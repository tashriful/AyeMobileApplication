package com.aye.web.repo.user;

import com.aye.web.model.user.UserAccessTemplateM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccessTemplateMRepo extends MongoRepository<UserAccessTemplateM, String> {
}
