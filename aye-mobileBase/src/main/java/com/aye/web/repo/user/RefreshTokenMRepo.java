package com.aye.web.repo.user;

import com.aye.web.model.user.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenMRepo extends MongoRepository<RefreshToken, String>{
    RefreshToken findByRefreshToken(String refreshToken);
}
