package com.aye.web.service;


import com.aye.web.model.user.UserAccessTemplateM;
import com.aye.web.repo.user.UserAccessTemplateMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccessTemplateMServiceImpl implements UserAccessTemplateMService {

    @Autowired
    private UserAccessTemplateMRepo userAccessTemplateMRepo;

    @Override
    public UserAccessTemplateM saveAccessTemplate(UserAccessTemplateM userAccessTemplateM) {
        return userAccessTemplateMRepo.save(userAccessTemplateM);
    }

    @Override
    public List<UserAccessTemplateM> getAllAccessTemplate() {
        return userAccessTemplateMRepo.findAll();
    }

    @Override
    public UserAccessTemplateM updateAccessTemplate(UserAccessTemplateM userAccessTemplateM) {
        return userAccessTemplateMRepo.save(userAccessTemplateM);
    }

    @Override
    public UserAccessTemplateM findAccessTemplateById(String id) {
        return userAccessTemplateMRepo.findById(id).orElse(null);
    }
}
