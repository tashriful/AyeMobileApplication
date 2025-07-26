package com.aye.web.repo;

import com.aye.web.model.order.OrdItemDetailM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdItemDetailMRepo extends MongoRepository<OrdItemDetailM, String> {

    OrdItemDetailM findByItemIdAndInvOrgId(int itemId, int invOrgId);
    List<OrdItemDetailM> findAllByOrgIdAndInvOrgId(int parentOrgId, int parentInvOrgId);
}
