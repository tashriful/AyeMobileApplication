package com.aye.web.service;

import com.aye.web.model.order.OrdItemDetailM;
import com.aye.web.repo.OrdItemDetailMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdItemDetailMServiceImpl implements OrdItemDetailMService {

    @Autowired
    private OrdItemDetailMRepo ordItemDetailMRepo;


    @Override
    public OrdItemDetailM findById(String id) {
        return ordItemDetailMRepo.findById(id).orElse(null);
    }

    @Override
    public OrdItemDetailM findByItemIdAndInvOrgId(int itemId, int invId) {
        return ordItemDetailMRepo.findByItemIdAndInvOrgId(itemId, invId);
    }

    @Override
    public List<OrdItemDetailM> findByParentOrgAndInvOrg(int parentOrg, int invOrg) {
        List<OrdItemDetailM> allByOrgIdAndInvOrgId = ordItemDetailMRepo.findAllByOrgIdAndInvOrgId(parentOrg, invOrg);
        if (allByOrgIdAndInvOrgId.size() != 0) {
            return allByOrgIdAndInvOrgId;
        }
        else return null;
    }
}
