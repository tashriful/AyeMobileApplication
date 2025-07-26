package com.aye.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrgHierarchyMDto {
    private String id;
    private String name;
    private String code;
    private String address;
    private OrgHierarchyMDto orgHierarchy;
    private Long parentAppOrgHierachyMId;

}
