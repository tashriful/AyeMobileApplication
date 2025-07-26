package com.aye.web.model.user;

import com.aye.web.model.common.InventoryInformationsAuth;
import com.aye.web.model.common.InventoryInformationsM;
import com.aye.web.model.common.OrgHierarchyM;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserMenuDetailsAuth {
    private String id;
    private String menuName;
    private UiPath uiPath;
    private boolean isActive;
    private OrgHierarchyM orgHierarchyM;
    private List<InventoryInformationsAuth> invOrgList;
}
