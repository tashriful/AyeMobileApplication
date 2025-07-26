package com.aye.web.model.order;

import com.aye.web.model.schedule.OrdDelvScheduleHeaderM;
import com.aye.web.model.schedule.OrdDelvScheduleLineM;
import com.aye.web.model.common.CustomerLineM;
import com.aye.web.model.common.MasterItemM;
import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.model.user.UserM;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

@Document(collection = "ORD_DELIVERY_LINE_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class DeliveryLineM {
    @Id
    private Long deliveryLineId;
    @DBRef
    @JsonBackReference
    @ToString.Exclude
    private DeliveryHeaderM deliveryHeader;
    @DBRef
    private OrgHierarchyM orgHierarchy;
    @DBRef
    private OrderHeaderM orderHeader;
    @DBRef
    private OrderTrnsTypeM orderTrnsType;
    @DBRef
    private OrderLineM orderLine;
    @DBRef
    private CustomerLineM customerLine;
    @DBRef
    private MasterItemM masterItem;
    private String itemCode;
    private String orderUomCode;
    private BigDecimal unitListPrice;
    private BigDecimal unitSellingPrice;
    private BigDecimal orderUomQty;
    private BigDecimal orderDefaultUomQty;
    private String defaultUomCode;
    private BigDecimal deliveryDefaultUomQty;
    private String deliveryUomCode;
    private BigDecimal deliveryUomQty;
    private BigDecimal convRate;
    private BigDecimal amount;
    private String subInvCode;
    @DBRef
    private OrdDelvScheduleHeaderM ordDelvScheduleHeader;
    @DBRef
    private OrdDelvScheduleLineM ordDelvScheduleLine;
    private BigDecimal defaultUomPrice;
    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;

}
