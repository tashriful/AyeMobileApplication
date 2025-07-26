package com.aye.web.model.ar;

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
public class PaymentsMSearchDto {

    private String id;

    private String payType;

    private String number;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date trnsDate;

    private String bankCode;

    private String orgHierarchyM;

    private String refNumber;
    private BigDecimal amount;
    private List<MultipartFile> file;
    private List<AttachmentM> attachmentM;

    private String customerLineM;

    private String bankLine;

    private String paymentStatus;

}
