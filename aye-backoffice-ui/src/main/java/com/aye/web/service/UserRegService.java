package com.aye.web.service;

import com.aye.web.model.UserDto;

import java.util.List;

public interface UserRegService {
    List<UserDto> getUsers(String accessToken);
    UserDto saveUser(UserDto userDto, String accessToken);

    UserDto getUserById(String id, String accessToken);
}
