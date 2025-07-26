package com.aye.web.service;



import com.aye.web.model.user.UserAccessTemplateDetailsM;
import com.aye.web.model.user.UserAccessTemplateM;

import java.util.List;

public interface UserAccessTemplateDetailsMService {
    UserAccessTemplateDetailsM saveTemplateDetails(UserAccessTemplateDetailsM userAccessTemplateDetailsM);
    List<UserAccessTemplateDetailsM> getAllTemplateDetails();
    UserAccessTemplateDetailsM updateTemplateDetails(UserAccessTemplateDetailsM userAccessTemplateDetailsM);
    UserAccessTemplateDetailsM findTemplateDetailsById(String id);
    List<UserAccessTemplateDetailsM> findTmpltDetailsByTmplt(UserAccessTemplateM userAccessTemplateM);
}
