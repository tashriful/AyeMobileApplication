package com.aye.web.service;

import com.aye.web.model.UserMenuDetailsDto;

import java.util.List;

public interface UserMenuDetailsService {
    List<UserMenuDetailsDto> getAllUserMenuDetails(String accessToken);

    List<UserMenuDetailsDto> getAllUserMenuDetailsByMenu(String accessToken, String menuId);
    UserMenuDetailsDto saveUserMenuDetails(UserMenuDetailsDto userMenuDetailsDto, String accessToken);
    UserMenuDetailsDto findUserMenuDetailsById(String id, String accessToken);
}
