package com.aye.web.service;

import com.aye.web.model.UserMenuDto;

import java.util.List;

public interface UserMenuService {
    List<UserMenuDto> findAllUserMenu(String accessToken);
    UserMenuDto saveUserMenu(UserMenuDto userMenuDto, String accessToken);
    UserMenuDto findUserMenuById(String id, String accessToken);

}
