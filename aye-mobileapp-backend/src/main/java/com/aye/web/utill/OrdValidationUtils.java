package com.aye.web.utill;


import com.aye.web.exception.GeneralExceptions;
import com.aye.web.model.ar.PaymentsM;
import com.aye.web.model.order.OrderHeaderM;
import com.aye.web.model.order.OrderLineM;
import com.aye.web.model.order.OrderStatusM;
import com.aye.web.model.order.OrderTrnsTypeM;
import com.aye.web.model.schedule.OrdDelvScheduleHeaderM;
import com.aye.web.model.schedule.ScdLineStatusM;
import com.aye.web.repo.*;
import com.aye.web.service.MasterItemMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class OrdValidationUtils {

    private final MasterItemMService masterItemMService;
    private final OrderHeaderMRepo orderHeaderMRepo;
    private final CustomerLineMRepo customerLineMRepo;
    private final BankLineMRepo bankLineMRepo;
    private final PaymentsMRepo paymentsMRepo;
    private final OrgHierarchyMRepo orgHierarchyMRepo;
    private final InventoryInformationsMRepo inventoryInformationsMRepo;
    private final OrderTrnsTypeMRepo orderTrnsTypeMRepo;
    private final OrdDelvSchdHeaderMRepo ordDelvSchdHeaderMRepo;
    private final OrderLineMRepo orderLineMRepo;

    @Autowired
    public OrdValidationUtils(MasterItemMService masterItemMService,
                              OrderHeaderMRepo orderHeaderMRepo,
                              CustomerLineMRepo customerLineMRepo, BankLineMRepo bankLineMRepo, PaymentsMRepo paymentsMRepo,
                              OrgHierarchyMRepo orgHierarchyMRepo,
                              InventoryInformationsMRepo inventoryInformationsMRepo,
                              OrderTrnsTypeMRepo orderTrnsTypeMRepo,
                              OrdDelvSchdHeaderMRepo ordDelvSchdHeaderMRepo,
                              OrderLineMRepo orderLineMRepo) {
        this.masterItemMService = masterItemMService;
        this.orderHeaderMRepo = orderHeaderMRepo;
        this.customerLineMRepo = customerLineMRepo;
        this.bankLineMRepo = bankLineMRepo;
        this.paymentsMRepo = paymentsMRepo;
        this.orgHierarchyMRepo = orgHierarchyMRepo;
        this.inventoryInformationsMRepo = inventoryInformationsMRepo;
        this.orderTrnsTypeMRepo = orderTrnsTypeMRepo;
        this.ordDelvSchdHeaderMRepo = ordDelvSchdHeaderMRepo;
        this.orderLineMRepo = orderLineMRepo;
    }


    public static void validateOrderStatus(OrderHeaderM oh) throws GeneralExceptions {
        if (!oh.getOrderStatus().equals(OrderStatusM.N)) {
            throw new GeneralExceptions("Order Status Already Posted", null);
        }
    }

    public void validateOrderHeader(String id) throws GeneralExceptions {
        if (id == null || id.isEmpty()) {
            throw new GeneralExceptions("Order Header Not Found", null);
        }
        Optional<OrderHeaderM> orderHeaderMRepoById = orderHeaderMRepo.findById(id);
        if (orderHeaderMRepoById.isEmpty()) {
            throw new GeneralExceptions("Order Header Not Valid", null);
        }
        if (!orderHeaderMRepoById.get().getOrderStatus().equals(OrderStatusM.N)) {
            throw new GeneralExceptions("Order Already Posted!", null);
        }
    }

    public void validatePayment(String id) throws GeneralExceptions {
        if (id == null || id.isEmpty()) {
            throw new GeneralExceptions("Recipt Empty!!", null);
        }
        Optional<PaymentsM> paymentsM = paymentsMRepo.findById(id);
        if (paymentsM.isEmpty()) {
            throw new GeneralExceptions("Invalid Recipt!", null);
        }
    }


    public void validateOrdTrnsType(String id) throws GeneralExceptions {
        if (id == null || id.isEmpty()) {
            throw new GeneralExceptions("Ord Trns Type Empty!!", null);
        }
        Optional<OrderTrnsTypeM> orderTrnsTypeM = orderTrnsTypeMRepo.findById(id);
        if (orderTrnsTypeM.isEmpty()) {
            throw new GeneralExceptions("Invalid Order Trns Type!", null);
        }
    }

    public void validateDlvSchdHeader(String id) throws GeneralExceptions {
        if (id == null || id.isEmpty()) {
            throw new GeneralExceptions("Ord Dlv Schd Header Empty!!", null);
        }
        Optional<OrdDelvScheduleHeaderM> delvScheduleHeaderM = ordDelvSchdHeaderMRepo.findById(id);
        if (delvScheduleHeaderM.isEmpty()) {
            throw new GeneralExceptions("Invalid Ord Dlv Schd Header!", null);
        }
        if (delvScheduleHeaderM.get().getStatus().equals(ScdLineStatusM.P)) {
            throw new GeneralExceptions("Dlv Schd Header Already Posted!", null);
        }
    }

    public void validateOrderLineWithHeader(String orderLineId, String orderHeaderId) throws GeneralExceptions {
        if (orderHeaderId == null || orderHeaderId.isEmpty() || orderLineId == null || orderLineId.isEmpty()) {
            throw new GeneralExceptions("OrderHeaderId or OrderLineId is null or empty", null);
        }
        Optional<OrderLineM> orderLineM = orderLineMRepo.findById(orderLineId);
        if (orderLineM.isEmpty()) {
            throw new GeneralExceptions("Order Line Invalid", null);
        }
        if (!orderLineM.get().getOrderHeader().getId().equals(orderHeaderId)) {
            throw new GeneralExceptions("Order Header not valid!", null);
        }
    }


    private boolean isZeroOrNullOrNegative(BigDecimal quantity) {
        return quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0;
    }

    private boolean isGreaterThanOrderLineQty(BigDecimal schdRelatedQty, BigDecimal ordLineQty) {
        return schdRelatedQty == null || ordLineQty == null || schdRelatedQty.compareTo(ordLineQty) > 0;
    }


}
