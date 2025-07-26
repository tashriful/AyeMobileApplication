package com.aye.web.repo.user;

import com.aye.web.model.user.UserAccessTemplateDetailsM;
import com.aye.web.model.user.UserInvOrgAccessM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInvOrgAccessMRepo extends MongoRepository<UserInvOrgAccessM, String> {
    List<UserInvOrgAccessM> findAllByUserAccessTemplateDetails(UserAccessTemplateDetailsM userAccessTemplateDetailsM);
}
