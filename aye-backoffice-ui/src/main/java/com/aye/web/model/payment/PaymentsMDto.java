package com.aye.web.model.payment;


import com.aye.web.model.BankLineMDto;
import com.aye.web.model.CustomerLineMDto;
import com.aye.web.model.OrgHierarchyMDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PaymentsMDto {

    private String id;

    private PaymentTypeDto payType;

    private String number;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date trnsDate;

    private String bankCode;

    private String refNumber;
    private BigDecimal amount;
    private List<MultipartFile> file;
    private List<AttachmentMDto> attachmentM;

    private CustomerLineMDto customerLineM;

    private OrgHierarchyMDto orgHierarchyM;

    private PaymentStatusDto paymentStatus;

    private BankLineMDto bankLine;
}
