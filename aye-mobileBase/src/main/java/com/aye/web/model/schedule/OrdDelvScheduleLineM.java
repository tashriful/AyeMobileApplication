package com.aye.web.model.schedule;

import com.aye.web.model.common.CustomerLineM;
import com.aye.web.model.common.MasterItemM;
import com.aye.web.model.order.OrderHeaderM;
import com.aye.web.model.order.OrderLineM;
import com.aye.web.model.order.OrderTrnsTypeM;
import com.aye.web.model.user.UserM;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mongodb.lang.Nullable;
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

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "ORD_DELV_SCHEDULE_LINES_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrdDelvScheduleLineM {

    @Id
    private String id;
    @DBRef
    @JsonBackReference
    @ToString.Exclude
    private OrdDelvScheduleHeaderM ordDelvScheduleHeader;
    @DBRef
    private OrderTrnsTypeM orderTrnsType;
    @DBRef
    @Nullable
    private CustomerLineM customerLine;
    @DBRef
    private OrderLineM orderLine;
    @DBRef
    private OrderHeaderM orderHeader;
    @DBRef
    @Nullable
    private MasterItemM masterItem;

    private String itemCode;

    private String orderUomCode;

    private BigDecimal scheduleQty;

    private BigDecimal approveQty;

    private BigDecimal approveDefaultUomQty;

    private BigDecimal convRate;


    private BigDecimal unitSellingPrice;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lineStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lineEndDate;

    private ScdLineStatusM scdLineStatus;

    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;
}
