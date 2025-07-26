package com.aye.web.service;


import com.aye.web.model.user.UserAccessTemplateM;

import java.util.List;

public interface UserAccessTemplateMService {
    UserAccessTemplateM saveAccessTemplate(UserAccessTemplateM userAccessTemplateM);
    List<UserAccessTemplateM> getAllAccessTemplate();
    UserAccessTemplateM updateAccessTemplate(UserAccessTemplateM userAccessTemplateM);
    UserAccessTemplateM findAccessTemplateById(String id);

}
