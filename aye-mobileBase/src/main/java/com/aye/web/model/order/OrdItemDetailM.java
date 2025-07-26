package com.aye.web.model.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "ORD_TRNS_TYPE_ITEM_DETAIL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrdItemDetailM {

    @Id
    private String id;
    private int ordTrnsTypeId;
    private int priceListHeaderIdob;
    private int priceListHeaderId;
    private int priceListName;
    private int priceListLineIdob;
    private int priceListLineId;
    private int invOrgId;
    private int itemId;
    private String itemCode;
    private String description;
    private int uomId;
    private String uomCode;
    private String priceUomCode;
    private int priceUomId;
    private BigDecimal amount;
    private int convId;
    private BigDecimal convRate;
    private int orgId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String name;
    private int currencyId;
    private int invoiceCategoryId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date priceListLineStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date priceListLineEndDate;
    private int ordTrnsInvOrgId;
    private int isDefaultUom;



}
