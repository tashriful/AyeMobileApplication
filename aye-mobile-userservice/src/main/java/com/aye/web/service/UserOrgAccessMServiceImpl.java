package com.aye.web.service;


import com.aye.web.model.user.UserAccessTemplateDetailsM;
import com.aye.web.model.user.UserOrgAccessM;
import com.aye.web.repo.user.UserAccessTemplateDetailsMRepo;
import com.aye.web.repo.user.UserOrgAccessMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserOrgAccessMServiceImpl implements UserOrgAccessMService {

    @Autowired
    private UserOrgAccessMRepo userOrgAccessMRepo;
    @Autowired
    private UserAccessTemplateDetailsMRepo userAccessTemplateDetailsMRepo;

    @Override
    public UserOrgAccessM saveUserOrgAccess(UserOrgAccessM userOrgAccessM) {
        UserOrgAccessM orgAccessM = userOrgAccessMRepo.save(userOrgAccessM);
        Optional<UserOrgAccessM> savedOrgAccess = userOrgAccessMRepo.findById(orgAccessM.getId());
        Optional<UserAccessTemplateDetailsM> userAccessTemplateDetailsM =
                userAccessTemplateDetailsMRepo.findById(orgAccessM.getUserAccessTemplateDetails().getId());
        List<UserOrgAccessM> orgAccessMList = new ArrayList<>();
        if(userAccessTemplateDetailsM.get().getUserOrgAccessMList() != null){
            orgAccessMList = userAccessTemplateDetailsM.get().getUserOrgAccessMList();
        }
        orgAccessMList.add(savedOrgAccess.get());
        userAccessTemplateDetailsM.get().setUserOrgAccessMList(orgAccessMList);
        userAccessTemplateDetailsMRepo.save(userAccessTemplateDetailsM.get());
        return orgAccessM;
    }

    @Override
    public List<UserOrgAccessM> getAllUserOrgAccess() {
        return userOrgAccessMRepo.findAll();
    }

    @Override
    public UserOrgAccessM getUserOrgAccessById(String id) {
        return userOrgAccessMRepo.findById(id).orElse(null);
    }

    @Override
    public UserOrgAccessM updateUserOrgAccess(UserOrgAccessM userOrgAccessM) {
        return userOrgAccessMRepo.save(userOrgAccessM);
    }
}
