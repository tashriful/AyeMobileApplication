package com.aye.web.service;


import com.aye.web.model.user.UserOrgAccessM;

import java.util.List;

public interface UserOrgAccessMService {

    UserOrgAccessM saveUserOrgAccess(UserOrgAccessM userOrgAccessM);
    List<UserOrgAccessM> getAllUserOrgAccess();
    UserOrgAccessM getUserOrgAccessById(String id);
    UserOrgAccessM updateUserOrgAccess(UserOrgAccessM userOrgAccessM);
}
