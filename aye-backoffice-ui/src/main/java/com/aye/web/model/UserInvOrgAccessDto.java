package com.aye.web.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserInvOrgAccessDto {
    private String id;
    private InvOrgMDto inventoryInformationsM;
    private AccessTemplateDetailsMDto userAccessTemplateDetails;
}
