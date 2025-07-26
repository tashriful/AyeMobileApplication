package com.aye.web.service;


import com.aye.web.model.user.UserAccessM;
import com.aye.web.model.user.UserM;
import com.aye.web.model.user.UserMenuAuth;

import java.util.List;

public interface UserAccessMService {

    UserAccessM saveUserAccess(UserAccessM userAccessM);
    UserAccessM getUserAccessById(String id);
    List<UserAccessM> getAllUserAccess();
    UserAccessM updateUserAccess(UserAccessM userAccessM);

    List<UserAccessM> getAllUserAccessByUser(UserM userM);

    List<UserMenuAuth> getAllUserAccessMenusByUser(UserM userM);
}
