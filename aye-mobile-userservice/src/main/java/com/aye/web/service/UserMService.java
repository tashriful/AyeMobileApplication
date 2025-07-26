package com.aye.web.service;


import com.aye.web.model.user.UserM;
import com.aye.web.model.user.UserRequestDto;

import java.util.List;

public interface UserMService {
    UserM saveUserM(UserM userM);
    List<UserM> getAllUserM();
    UserM findUserMById(String id);
    UserM updateUserM(UserM userM);

    void authenticateUser(UserRequestDto userRequestDto);

    UserM findUserMByUserName(String username);
}
