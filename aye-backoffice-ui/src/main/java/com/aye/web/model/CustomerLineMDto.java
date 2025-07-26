package com.aye.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomerLineMDto {

    private String id;

    private String siteName;

    private String siteAddress;

    private CommonStatusMDto status;

    private String name;

    private BigDecimal creditLimit;

    private CommonEnumMDto.CustomerTypes customerTypes;

    @ToString.Exclude
    private OrgHierarchyMDto orgHierarchy;

    private Long parentAppCustomerLineMId;

}
