package com.aye.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AccessTemplateDetailsMDto {

    private String id;
    private String tmplDetailName;

    private OrgHierarchyMDto orgHierarchyM;
    private String orgHierarchyId;

    private UserMenuDto userMenuM;
    private String userMenuId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private AccessTemplateDto userAccessTemplateM;

    private List<UserInvOrgAccessDto> userInvOrgAccessMList;

}
