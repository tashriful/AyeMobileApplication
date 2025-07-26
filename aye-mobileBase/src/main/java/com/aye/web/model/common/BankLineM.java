package com.aye.web.model.common;

import com.aye.web.model.user.UserM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "BANK_LINE_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BankLineM {

    @Id
    private String id;

    private String name;

    private String address;

    private String accountNumber;

    private String accountName;

    private String branch;

    private String bankAccountType;

    private String phone;

    private String email;

    @DBRef
    private OrgHierarchyM orgHierarchyM;

    private Long parentAppBankLineId;
    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;

}
