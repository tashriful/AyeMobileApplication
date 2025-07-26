package com.aye.web.service;

import com.aye.web.dto.ApiResponse;
import com.aye.web.model.order.OrderHeaderMDto;
import com.aye.web.model.order.OrderStatusMDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderUiServiceImpl implements OrderUiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${msales.baseurl}")
    private String msalesBaseUrl;

    @Override
    public List<OrderHeaderMDto> getAllPostedOrder(String accessToken, OrderStatusMDto orderStatusMDto) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/main/ord/getOrderHeaders/status/"+ orderStatusMDto;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<OrderHeaderMDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<ApiResponse<List<OrderHeaderMDto>>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<ApiResponse<List<OrderHeaderMDto>>>(){});
        return response.getBody().getResponseData();
    }

    @Override
    public OrderHeaderMDto findOrderById(String orderHeaderId, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/main/ord/orderHeaders/"+ orderHeaderId;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<OrderHeaderMDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<ApiResponse<OrderHeaderMDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity,  new ParameterizedTypeReference<ApiResponse<OrderHeaderMDto>>() {}
        );
        return response.getBody().getResponseData();
    }

    @Override
    public String approveOrderHeader(String orderHeaderId, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/main/ord/orderHeaders/"+ orderHeaderId + "/status/A";

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<String> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<ApiResponse<String>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
        });
        return response.getBody().getMessage();
    }


}
