package com.aye.web.service;


import com.aye.web.model.user.UserAccessTemplateDetailsM;
import com.aye.web.model.user.UserInvOrgAccessM;
import com.aye.web.repo.user.UserAccessTemplateDetailsMRepo;
import com.aye.web.repo.user.UserInvOrgAccessMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserInvOrgAccessMServiceImpl implements UserInvOrgAccessMService {

    @Autowired
    private UserInvOrgAccessMRepo userInvOrgAccessMRepo;
    @Autowired
    private UserAccessTemplateDetailsMRepo userAccessTemplateDetailsMRepo;

    @Override
    public UserInvOrgAccessM saveUserInvOrgAccess(UserInvOrgAccessM userInvOrgAccessM) {
        UserInvOrgAccessM invOrgAccessM = userInvOrgAccessMRepo.save(userInvOrgAccessM);
        Optional<UserInvOrgAccessM> savedInvOrgAccess = userInvOrgAccessMRepo.findById(invOrgAccessM.getId());
        Optional<UserAccessTemplateDetailsM> userAccessTemplateDetailsM = userAccessTemplateDetailsMRepo.findById(savedInvOrgAccess.get().getUserAccessTemplateDetails().getId());
        List<UserInvOrgAccessM> userInvOrgAccessMList = new ArrayList<>();
        if(userAccessTemplateDetailsM.get().getUserInvOrgAccessMList() != null){
            userInvOrgAccessMList = userAccessTemplateDetailsM.get().getUserInvOrgAccessMList();
        }
        userInvOrgAccessMList.add(savedInvOrgAccess.get());
        userAccessTemplateDetailsM.get().setUserInvOrgAccessMList(userInvOrgAccessMList);
        userAccessTemplateDetailsMRepo.save(userAccessTemplateDetailsM.get());
        return invOrgAccessM;
    }

    @Override
    public List<UserInvOrgAccessM> getAllUserInvOrgAccess() {
        return userInvOrgAccessMRepo.findAll();
    }

    @Override
    public List<UserInvOrgAccessM> getAllInvOrgAccessByTmpltDtl(String tmpltDtlId) {
        Optional<UserAccessTemplateDetailsM> templateDetail= userAccessTemplateDetailsMRepo.findById(tmpltDtlId);
        if (templateDetail.isPresent()) {
            List<UserInvOrgAccessM> allinvOrgAccess = userInvOrgAccessMRepo.findAllByUserAccessTemplateDetails(templateDetail.get());
            return allinvOrgAccess;
        }
        else
            return null;
    }

    @Override
    public UserInvOrgAccessM getUserInvOrgAccessById(String id) {
        return userInvOrgAccessMRepo.findById(id).orElse(null);
    }

    @Override
    public UserInvOrgAccessM updateUserInvOrgAccess(UserInvOrgAccessM userInvOrgAccessM) {
        return userInvOrgAccessMRepo.save(userInvOrgAccessM);
    }
}
