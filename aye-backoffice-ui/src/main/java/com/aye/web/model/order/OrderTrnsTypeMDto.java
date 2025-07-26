package com.aye.web.model.order;



import com.aye.web.model.InventoryInformationsMDto;
import com.aye.web.model.OrgHierarchyMDto;
import lombok.*;


import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class OrderTrnsTypeMDto {

    private String id;
    private String trnsTypeName;
    private String description;
    private Boolean active;
    private OrgHierarchyMDto orgHierarchy;
    private InventoryInformationsMDto invOrg;
    private Long parentAppTrnsTypeMId;



}
