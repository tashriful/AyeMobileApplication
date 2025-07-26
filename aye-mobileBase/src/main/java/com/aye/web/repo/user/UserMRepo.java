package com.aye.web.repo.user;

import com.aye.web.model.user.UserM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMRepo extends MongoRepository<UserM, String> {

    UserM findUserMByUsername(String username);
}
