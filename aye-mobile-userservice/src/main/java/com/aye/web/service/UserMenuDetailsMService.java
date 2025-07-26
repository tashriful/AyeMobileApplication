package com.aye.web.service;



import com.aye.web.model.user.UserMenuDetailsM;
import com.aye.web.model.user.UserMenuDetailsMDto;
import com.aye.web.model.user.UserMenuM;

import java.util.List;

public interface UserMenuDetailsMService {

    UserMenuDetailsM saveUserMenuDetails(UserMenuDetailsM userMenuDetailsM);
    List<UserMenuDetailsM> getAllUserMenuDetails();
    UserMenuDetailsM updateUserMenuDetails(UserMenuDetailsM userMenuDetailsM);
    UserMenuDetailsM findUserMenuDetailsById(String id);

    List<UserMenuDetailsM> findUserMenuDetailsByUserMenu(UserMenuM userMenuM);
}
