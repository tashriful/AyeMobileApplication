package com.aye.web.model;


import lombok.*;


import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class InventoryInformationsMDto {

    private String id;

    private String code;

    private String name;

    private String address;

    private OrgHierarchyMDto orgHierarchy;

    private InventoryInformationsMDto itemOrg;

    private Boolean isItemMaster;

    private Long batchCirculationLoop;

    private Long parentAppInvInfoMId;
}
