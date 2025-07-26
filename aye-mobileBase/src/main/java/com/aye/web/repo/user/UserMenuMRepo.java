package com.aye.web.repo.user;

import com.aye.web.model.user.UserMenuM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMenuMRepo extends MongoRepository<UserMenuM, String> {
}
