package com.aye.web.service;

import com.aye.web.model.user.UserInvOrgAccessM;

import java.util.List;

public interface UserInvOrgAccessMService {
    UserInvOrgAccessM saveUserInvOrgAccess(UserInvOrgAccessM userInvOrgAccessM);
    List<UserInvOrgAccessM> getAllUserInvOrgAccess();

    List<UserInvOrgAccessM> getAllInvOrgAccessByTmpltDtl(String tmpltDtlId);
    UserInvOrgAccessM getUserInvOrgAccessById(String id);
    UserInvOrgAccessM updateUserInvOrgAccess(UserInvOrgAccessM userInvOrgAccessM);
}
