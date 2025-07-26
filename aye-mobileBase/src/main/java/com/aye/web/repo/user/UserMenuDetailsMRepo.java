package com.aye.web.repo.user;

import com.aye.web.model.user.UserMenuDetailsM;
import com.aye.web.model.user.UserMenuM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMenuDetailsMRepo extends MongoRepository<UserMenuDetailsM, String> {
    List<UserMenuDetailsM> findUserMenuDetailsMByUserMenuM(UserMenuM userMenuM);
}
