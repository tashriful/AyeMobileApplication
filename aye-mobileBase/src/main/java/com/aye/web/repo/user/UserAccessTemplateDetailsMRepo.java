package com.aye.web.repo.user;

import com.aye.web.model.user.UserAccessTemplateDetailsM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccessTemplateDetailsMRepo extends MongoRepository<UserAccessTemplateDetailsM, String> {
    List<UserAccessTemplateDetailsM> findUserAccessTemplateDetailsMByUserAccessTemplateMId(String userAccessTmpltId);
}
