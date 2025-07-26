package com.aye.web.model.ar;

import com.aye.web.model.common.BankLineM;
import com.aye.web.model.common.CustomerLineM;
import com.aye.web.model.common.OrgHierarchyM;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Document(collection = "PAYMENTS_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PaymentsM {

    @Id
    private String id;

    private PaymentType payType;

    private String number;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date trnsDate;

    private String bankCode;

    private String refNumber;
    private BigDecimal amount;
    private List<MultipartFile> file;
    private List<AttachmentM> attachmentM;

    @DBRef
    private CustomerLineM customerLineM;

    @DBRef
    private OrgHierarchyM orgHierarchyM;

    private PaymentStatus paymentStatus;

    @DBRef
    private BankLineM bankLine;
    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;
}
