package com.aye.web.service;

import com.aye.web.model.UserAccessMDto;

import java.util.List;

public interface UserAccessService {
    List<UserAccessMDto> getUserAccessByUser(String accessToken, String userId);
    UserAccessMDto saveUserAccess(UserAccessMDto userAccessMDto, String accessToken);
    UserAccessMDto findUserAccessById(String id, String accessToken);
}
