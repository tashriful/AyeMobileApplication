package com.aye.web.repo.user;

import com.aye.web.model.user.UserAccessM;
import com.aye.web.model.user.UserM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccessMRepo extends MongoRepository<UserAccessM, String> {
    List<UserAccessM> findUserAccessMByUser(UserM userM);
}
