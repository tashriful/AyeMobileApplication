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
public class BankLineMDto {

    private String id;

    private String name;

    private String address;

    private String accountNumber;

    private String accountName;

    private String branch;

    private String bankAccountType;

    private String phone;

    private String email;

    private OrgHierarchyMDto orgHierarchyM;

    private Long parentAppBankLineId;

}
