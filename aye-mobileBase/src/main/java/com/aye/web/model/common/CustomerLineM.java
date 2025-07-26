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

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "CUSTOMER_LINE_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomerLineM {

    @Id
    private String id;

    private String siteName;

    private String siteAddress;

    private CommonStatusM status;

    private String name;

    private BigDecimal creditLimit;

    private CommonEnumM.CustomerTypes customerTypes;

    @DBRef
    @ToString.Exclude
    private OrgHierarchyM orgHierarchy;

    private Long parentAppCustomerLineMId;

    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;
}
