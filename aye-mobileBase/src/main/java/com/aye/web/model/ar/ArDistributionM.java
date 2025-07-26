package com.aye.web.model.ar;

import com.aye.web.model.order.OrderHeaderM;
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

@Document(collection ="AR_DISTRIBUTION_M" )
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ArDistributionM {

    @Id
    private String id;

    @DBRef
    private PaymentsM receipt;

    private BigDecimal reciptAmount;

    private Date date;

    private Date trnsDate;

    private String chqNumber;

    private BigDecimal chqAmount;

    @DBRef
    private OrderHeaderM orderHeaderM;

    private BigDecimal orderAmount;

    private BigDecimal distributedAmount;
    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;

}
