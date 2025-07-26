package com.aye.web.utill;

import com.aye.web.mobileBase.exception.GeneralExceptions;
import com.aye.web.mobileBase.model.order.OrderLineM;
import com.aye.web.mobileBase.model.schedule.OrdDelvScheduleHeaderM;
import com.aye.web.mobileBase.model.schedule.ScdLineStatusM;
import com.aye.web.mobileBase.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class DlvValidationUtils {

    private final OrdDelvSchdHeaderMRepo ordDelvSchdHeaderMRepo;
    private final OrderLineMRepo orderLineMRepo;
    private final RestTemplate restTemplate;

    @Autowired
    public DlvValidationUtils(OrdDelvSchdHeaderMRepo ordDelvSchdHeaderMRepo, OrderLineMRepo orderLineMRepo, RestTemplate restTemplate) {
        this.ordDelvSchdHeaderMRepo = ordDelvSchdHeaderMRepo;
        this.orderLineMRepo = orderLineMRepo;
        this.restTemplate = restTemplate;
    }


    public void validateOrderLineWithHeader(String id) throws GeneralExceptions {
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

    public void validateSchdQuantity(BigDecimal scheduleQty, BigDecimal approveQty, BigDecimal approveDefaultUomQty,
                                     OrderLineM orderLineM) throws GeneralExceptions {

        String url = "http://MAIN-ORDER-SERVICE/order/getOrderLine/" + orderLineM.getLineId();  // Replace with your actual API Gateway base URL

        // Make the GET request using RestTemplate
        ResponseEntity<OrderLineM> orderLineMResponseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                OrderLineM.class
        );

        System.out.println(orderLineMResponseEntity);


        BigDecimal orderLineUomQty = null;
        if (orderLineMResponseEntity.getBody() != null) {
            orderLineUomQty = orderLineMResponseEntity.getBody().getOrderUomQty();
        }

        if (isZeroOrNullOrNegative(scheduleQty)) {
            throw new GeneralExceptions("Schedule quantity must be greater than 0 and not null", null);
        }

        if (isZeroOrNullOrNegative(approveQty)) {
            throw new GeneralExceptions("Approve quantity must be greater than 0 and not null", null);
        }

        if (isZeroOrNullOrNegative(approveDefaultUomQty)) {
            throw new GeneralExceptions("Approve default UOM quantity must be greater than 0 and not null", null);
        }
        if (isGreaterThanOrderLineQty(scheduleQty, orderLineUomQty)) {
            throw new GeneralExceptions("Schedule quantity can not be greater than order qty", null);
        }
        if (isGreaterThanOrderLineQty(approveQty, orderLineUomQty)) {
            throw new GeneralExceptions("approveQty can not be greater than order qty", null);
        }
        if (isGreaterThanOrderLineQty(approveDefaultUomQty, orderLineUomQty)) {
            throw new GeneralExceptions("Approve default UOM quantity can not be greater than order qty", null);
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

    private boolean isZeroOrNullOrNegative(BigDecimal quantity) {
        return quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0;
    }

    private boolean isGreaterThanOrderLineQty(BigDecimal schdRelatedQty, BigDecimal ordLineQty) {
        return schdRelatedQty == null || ordLineQty == null || schdRelatedQty.compareTo(ordLineQty) > 0;
    }


}
