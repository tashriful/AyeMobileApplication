package com.aye.web.service;

import com.aye.web.model.UserInvOrgAccessDto;
import com.aye.web.model.UserMenuDetailsDto;

import java.util.List;

public interface UserInvOrgAccessService {
    List<UserInvOrgAccessDto> getAllInvOrgAccessByTmpltDtl(String tmpltDtlId, String accessToken);

    //    List<UserMenuDetailsDto> getAllUserMenuDetailsByMenu(String accessToken, String menuId);
    UserInvOrgAccessDto saveUserInvOrgAccess(UserInvOrgAccessDto userInvOrgAccessDto, String accessToken);
//    UserMenuDetailsDto findUserMenuDetailsById(String id, String accessToken);
}
