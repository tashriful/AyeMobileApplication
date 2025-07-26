package com.aye.web.repo.user;

import com.aye.web.model.user.UserOrgAccessM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrgAccessMRepo extends MongoRepository<UserOrgAccessM, String> {
}
