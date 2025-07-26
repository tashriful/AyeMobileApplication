package com.aye.web.service;

import com.aye.web.exception.CustomExceptions;
import com.aye.web.exception.GeneralExceptions;
import com.aye.web.utill.OrdValidationUtils;
import com.aye.web.model.common.CustomerLineM;
import com.aye.web.model.common.OrgHierarchyM;
import com.aye.web.model.order.OrderHeaderM;
import com.aye.web.model.order.OrderHeaderMSearchDto;
import com.aye.web.model.order.OrderLineM;
import com.aye.web.model.order.OrderStatusM;
import com.aye.web.repo.ArDistributionMRepo;
import com.aye.web.repo.OrderHeaderMRepo;
import com.aye.web.repo.OrderLineMRepo;
import com.aye.web.utill.BaseValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class OrderServiceMImpl implements OrderServiceM {

    private final OrderHeaderMRepo orderHeaderMRepo;
    private final OrderLineMRepo orderLineMRepo;
    private final MongoTemplate mongoTemplate;
    private final ArDistributionMRepo arDistributionRepo;
    private final CustomerServiceM customerService;
    private final OrdItemDetailMService ordItemDetailMService;

    private final OrgHierarchyMService orgHierarchyMService;

    private final InventoryInformationsMService inventoryInformationsMService;

    private final OrderTrnsTypeMService orderTrnsTypeMService;

    private final OrdValidationUtils validationUtils;

    private final BaseValidationUtils baseValidationUtils;
    private final RestTemplate restTemplate;

    @Autowired
    public OrderServiceMImpl(
            OrderHeaderMRepo orderHeaderMRepo,
            OrderLineMRepo orderLineMRepo,
            MongoTemplate mongoTemplate,
            ArDistributionMRepo arDistributionRepo,
            CustomerServiceM customerService,
            OrdItemDetailMService ordItemDetailMService,
            OrgHierarchyMService orgHierarchyMService, InventoryInformationsMService inventoryInformationsMService, OrderTrnsTypeMService orderTrnsTypeMService, OrdValidationUtils validationUtils, BaseValidationUtils baseValidationUtils, RestTemplate restTemplate) {
        this.orderHeaderMRepo = orderHeaderMRepo;
        this.orderLineMRepo = orderLineMRepo;
        this.mongoTemplate = mongoTemplate;
        this.arDistributionRepo = arDistributionRepo;
        this.customerService = customerService;
        this.ordItemDetailMService = ordItemDetailMService;
        this.orgHierarchyMService = orgHierarchyMService;
        this.inventoryInformationsMService = inventoryInformationsMService;
        this.orderTrnsTypeMService = orderTrnsTypeMService;
        this.validationUtils = validationUtils;
        this.baseValidationUtils = baseValidationUtils;
        this.restTemplate = restTemplate;
    }


    @Override
    public OrderHeaderM saveOrderHeader(OrderHeaderM orderHeader) throws GeneralExceptions {
        checkB4Save(orderHeader);
        if (Objects.equals(orderHeader.getOrderNumber(), "") || orderHeader.getOrderNumber() == null) {
            orderHeader.setOrderNumber(getOrderNumber(orderHeader.getOrgHierarchy()));
        }
        return orderHeaderMRepo.save(orderHeader);
    }

    private String getOrderNumber(OrgHierarchyM orgHierarchy) {
        OrderHeaderM prevOrderH = orderHeaderMRepo.findTopByOrgHierarchyOrderByOrderNumberDesc(orgHierarchy);
        if (prevOrderH != null) {
            int orderNumber = Integer.parseInt(prevOrderH.getOrderNumber());
            return String.format("%09d", orderNumber + 1);
        } else {
            return String.format("%09d", 1);
        }

    }

    private void checkB4Save(OrderHeaderM oh) throws GeneralExceptions {

        if (!oh.getOrderStatus().equals(OrderStatusM.N)) {
            throw new GeneralExceptions("Order Status Already Posted", null);
        }

        if ((oh.getCustomerLine() == null) || oh.getCustomerLine().getId() == null) {
            throw new GeneralExceptions("Customer Id Missing!", null);
        }
        if (oh.getCustomerLine() != null) {
            CustomerLineM customerLineM = customerService.findById(oh.getCustomerLine().getId());
            if (customerLineM == null) {
                throw new GeneralExceptions("Invalid Customer", null);
            }

        }

        if (oh.getOrgHierarchy().getId() == null || oh.getInventoryInformations().getId() == null) {
            throw new GeneralExceptions("Organization and Inv Org Can't be Empty", null);
        }
        if (orgHierarchyMService.findOrgHierarchyMById(oh.getOrgHierarchy().getId()) == null) {
            throw new GeneralExceptions("Invalid Organization!", null);
        }
        if (inventoryInformationsMService.findInventoryInformationsMById(oh.getInventoryInformations().getId()) == null) {
            throw new GeneralExceptions("Invalid Inventory Org!", null);
        }
        if (oh.getOrderTrnsType().getId() == null || oh.getOrderTrnsType().getId() == null) {
            throw new GeneralExceptions("Order Trns Type Can't be Empty", null);
        }
        if (orderTrnsTypeMService.findById(oh.getOrderTrnsType().getId()) == null) {
            throw new GeneralExceptions("Invalid Transaction Type!", null);
        }


    }

    @Override
    public OrderHeaderM findByHeaderId(String headerId) {
        return this.orderHeaderMRepo.findById(headerId).orElseThrow(()->new CustomExceptions.ResourceNotFoundException("Order Not Found"));
    }

    @Override
    public OrderHeaderM updateOrderHeader(OrderHeaderM orderHeader) {
        return orderHeaderMRepo.save(orderHeader);
    }

    @Override
    public List<OrderHeaderM> findOrderHeaderByStatus(OrderStatusM orderStatusM) {
        List<OrderHeaderM> allByOrderStatus = orderHeaderMRepo.findAllByOrderStatus(orderStatusM);
        if (allByOrderStatus != null) {
            return allByOrderStatus;
        }
        else return null;
    }

    @Override
    public OrderLineM saveOrderLine(OrderLineM orderLineM) throws GeneralExceptions {
        checkBeforeSaveLine(orderLineM);
        BigDecimal amount = new BigDecimal(0);
        amount = orderLineM.getPrice().multiply(orderLineM.getOrderUomQty()).setScale(2, RoundingMode.HALF_UP);
        orderLineM.setAmount(amount);
//        if (orderLineM.getLineId() != null){
//            orderLineM.setUpdatedAt(new Date());
//        }
//        else orderLineM.setUpdatedAt(new Date());

        OrderLineM orderLineM1 = orderLineMRepo.save(orderLineM);
        Optional<OrderLineM> orderLineM2 = orderLineMRepo.findById(orderLineM1.getId());
        updateOrderLineofOrdHeader(orderLineM2.get());
        return orderLineM2.get();
    }

    private void updateOrderLineofOrdHeader(OrderLineM orderLineM2) {
        Optional<OrderHeaderM> orderHeaderM = orderHeaderMRepo.findById(orderLineM2.getOrderHeader().getId());
        List<OrderLineM> orderLineMList = new ArrayList<>();
        if (orderHeaderM.get().getOrderLineMList() != null) {
            orderLineMList = orderHeaderM.get().getOrderLineMList();
        }
        orderLineMList.add(orderLineM2);
        orderHeaderM.get().setOrderLineMList(orderLineMList);
        orderHeaderMRepo.save(orderHeaderM.get());
    }

    private void checkBeforeSaveLine(OrderLineM ol) throws GeneralExceptions {

        validationUtils.validateOrderHeader(ol.getOrderHeader().getId());
        baseValidationUtils.validateItemDetail(ol.getOrdItemDetailM().getId());
        if (ol.getOrderDefaultUomQty().compareTo(BigDecimal.ZERO) == 0 || ol.getOrderUomQty().compareTo(BigDecimal.ZERO) == 0) {
            throw new GeneralExceptions("Quantity Can't be 0", null);
        }
    }

    @Override
    public List<OrderLineM> findAllOrderLine() {
        return orderLineMRepo.findAll();
    }

    @Override
    public OrderLineM findOrderLineByID(String lineId) throws GeneralExceptions {
        Optional<OrderLineM> orderLineM = orderLineMRepo.findById(lineId);
       if (orderLineM.isPresent()){
           return orderLineM.get();
       }
       throw new GeneralExceptions("Order Line Not Found!", null);
    }

    @Override
    public List<OrderHeaderM> findAllOrder() {
        return this.orderHeaderMRepo.findAll();
    }

    @Override
    public List<OrderLineM> findOrderLinesByOrderHeader(String headerId) {
        return this.orderLineMRepo.findAllByOrderHeaderId(headerId);
    }

    @Override
    public Page<OrderHeaderM> getAllOrder(OrderHeaderMSearchDto orderHeaderSearchDto, Pageable pageable) {
        Query query = new Query().with(pageable).with(Sort.by(Sort.Order.asc("orderNumber")));

        List<Criteria> criterias = new ArrayList<>();

        //mandatory search fields. Ig no data given it will store null and no data found in search result
        criterias.add(Criteria.where("orgHierarchy").is(orderHeaderSearchDto.getOrgHierarchy()));
        criterias.add(Criteria.where("inventoryInformations").is(orderHeaderSearchDto.getInventoryInformations()));


        if (orderHeaderSearchDto.getId() != null && !orderHeaderSearchDto.getId().isBlank()) {
            criterias.add(Criteria.where("id").is(orderHeaderSearchDto.getId()));
        }
        if (orderHeaderSearchDto.getOrderTrnsType() != null && !orderHeaderSearchDto.getOrderTrnsType().isBlank()) {
            criterias.add(Criteria.where("orderTrnsType").is(orderHeaderSearchDto.getOrderTrnsType()));
        }
        if (orderHeaderSearchDto.getOrderNumber() != null && !orderHeaderSearchDto.getOrderNumber().isBlank()) {
            criterias.add(Criteria.where("orderNumber").is(orderHeaderSearchDto.getOrderNumber()));
        }
        if (orderHeaderSearchDto.getOrderDate() != null) {
            criterias.add(Criteria.where("orderDate").is(orderHeaderSearchDto.getOrderDate()));
        }
        if (orderHeaderSearchDto.getOrderStatus() != null && !orderHeaderSearchDto.getOrderStatus().isBlank()) {
            criterias.add(Criteria.where("orderStatus").is(orderHeaderSearchDto.getOrderStatus()));
        }
//        if(orderHeaderSearchDto.getOrgHierarchy() != null && !orderHeaderSearchDto.getOrgHierarchy().isBlank()){
//
//        }
        if (orderHeaderSearchDto.getCustomerLine() != null && !orderHeaderSearchDto.getCustomerLine().isBlank()) {
            criterias.add(Criteria.where("customerLine").is(orderHeaderSearchDto.getCustomerLine()));
        }

        if (!criterias.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[0])));
        }

        return PageableExecutionUtils.getPage(mongoTemplate.find(query, OrderHeaderM.class), pageable,
                () -> mongoTemplate.count(query.skip(0).limit(0), OrderHeaderM.class));
    }

    @Override
    @Transactional
    public void deleteOrderLine(String headerId, String lineId) throws GeneralExceptions {
        Optional<OrderHeaderM> orderHeader = orderHeaderMRepo.findById(headerId);
        boolean isLineExist = orderHeader.get().getOrderLineMList().stream().anyMatch(ol -> ol.getId().equals(lineId));
        if (!isLineExist) {
            throw new GeneralExceptions("Order line does not exist", null);
        }

        Optional<OrderLineM> orderLineM = orderLineMRepo.findById(lineId);
        orderLineM.ifPresent(orderLineMRepo::delete);

        if (orderHeader.isPresent()) {
            if (orderHeader.get().getOrderStatus().equals(OrderStatusM.P)){
                throw new GeneralExceptions("Order Status is Posted", null);
            }
            List<OrderLineM> orderLineMList = orderHeader.get().getOrderLineMList();
            if (orderLineMList != null) {
                orderLineMList.removeIf(orderLineM1 -> orderLineM1.getId().equals(lineId));
            }
            orderHeaderMRepo.save(orderHeader.get());
        }

    }

    @Override
    public void deleteOrderHeader(String headerId) throws GeneralExceptions {
        Optional<OrderHeaderM> orderHeaderM = orderHeaderMRepo.findById(headerId);
        if (orderHeaderM.isPresent()){
            if (orderHeaderM.get().getOrderStatus().equals(OrderStatusM.P)){
                throw new GeneralExceptions("Can't be deleted, status is Posted!", null);
            }
            List<OrderLineM> orderLineMList = orderHeaderM.get().getOrderLineMList();
            if (orderLineMList != null) {
                orderLineMRepo.deleteAll(orderLineMList);
            }
            orderHeaderMRepo.delete(orderHeaderM.get());
        }
    }

    @Override
    public void updateOrderHeaderStatus(String headerId, String headerStatus) throws GeneralExceptions {
        Optional<OrderHeaderM> orderHeaderM = orderHeaderMRepo.findById(headerId);

        if (orderHeaderM.isPresent()) {
            if (headerStatus.equals("P")) {

                if (orderHeaderM.get().getOrderLineMList() == null) {
                    throw new GeneralExceptions("No Order Line Found!", null);
                }
                if (orderHeaderM.get().getOrderStatus().equals(OrderStatusM.P)) {
                    throw new GeneralExceptions("Order Already Posted!", null);
                }
                orderHeaderM.get().setOrderStatus(OrderStatusM.P);
                orderHeaderMRepo.save(orderHeaderM.get());
            }
            else if (headerStatus.equals("A")) {
                if (orderHeaderM.get().getOrderLineMList() == null){
                    throw new GeneralExceptions("No Order Line Found!", null);
                }
                if (orderHeaderM.get().getOrderStatus().equals(OrderStatusM.A)){
                    throw new GeneralExceptions("Order Already Approved!", null);
                }
                orderHeaderM.get().setOrderStatus(OrderStatusM.A);
                orderHeaderMRepo.save(orderHeaderM.get());
            }
            else {
                throw new GeneralExceptions("Order Status Not Valid!", null);
            }

        }
        else {
            throw new GeneralExceptions("Order Header Not Found!", null);
        }
    }

    @Override
    public void approveOrderHeader(String headerId) throws GeneralExceptions {
        Optional<OrderHeaderM> orderHeaderM = orderHeaderMRepo.findById(headerId);
        if (orderHeaderM.isPresent()){
            if (orderHeaderM.get().getOrderLineMList() == null){
                throw new GeneralExceptions("No Order Line Found!", null);
            }
            if (orderHeaderM.get().getOrderStatus().equals(OrderStatusM.A)){
                throw new GeneralExceptions("Order Already Approved!", null);
            }
            orderHeaderM.get().setOrderStatus(OrderStatusM.A);
            orderHeaderMRepo.save(orderHeaderM.get());
        }
    }


}
