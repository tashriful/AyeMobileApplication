package com.aye.web.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class InvOrgMDto {

    private String id;

    private String code;

    private String name;

    private String address;

    private String orgHierarchyId;

    private OrgHierarchyMDto orgHierarchy;

    private Long parentAppInvInfoMId;

}
