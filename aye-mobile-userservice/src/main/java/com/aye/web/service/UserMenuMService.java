package com.aye.web.service;

import com.aye.web.model.user.UserMenuM;

import java.util.List;

public interface UserMenuMService {
    UserMenuM saveUserMenu(UserMenuM userMenuM);
    List<UserMenuM> getAllUserMenu();
    UserMenuM updateUserMenu(UserMenuM userMenuM);
    UserMenuM getUserMenuById(String id);


}
