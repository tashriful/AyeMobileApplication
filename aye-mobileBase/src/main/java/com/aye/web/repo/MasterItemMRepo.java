package com.aye.web.repo;

import com.aye.web.model.common.InventoryInformationsM;
import com.aye.web.model.common.MasterItemM;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MasterItemMRepo extends MongoRepository<MasterItemM, String> {
    List<MasterItemM> findAllByInventoryInformationsM(InventoryInformationsM inventoryInformationsM);
    MasterItemM findByInventoryInformationsMAndId(InventoryInformationsM inventoryInformationsM, String id);
    List<MasterItemM> findAllByInventoryInformationsMAndItemCodeLikeIgnoreCase(InventoryInformationsM inventoryInformationsM,
                                                                               String itemCode);
}
