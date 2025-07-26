package com.aye.web.service;

import com.aye.web.dto.ApiResponse;
import com.aye.web.model.order.OrderHeaderMDto;
import com.aye.web.model.payment.PaymentStatusDto;
import com.aye.web.model.payment.PaymentsMDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PaymentUiServiceImpl implements PaymentUiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${msales.baseurl}")
    private String msalesBaseUrl;

    @Override
    public List<PaymentsMDto> getAllPostedPayment(String accessToken, PaymentStatusDto paymentStatusDto) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/main/payments/status/"+ paymentStatusDto;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<OrderHeaderMDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<PaymentsMDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<List<PaymentsMDto>>(){});
        return response.getBody();
    }

    @Override
    public PaymentsMDto findPaymentById(String paymentId, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/main/payments/"+ paymentId;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<PaymentsMDto> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<ApiResponse<PaymentsMDto>> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, new ParameterizedTypeReference<ApiResponse<PaymentsMDto>>() {
        });
        return response.getBody().getResponseData();
    }

    @Override
    public String approvePayment(String paymentId, String accessToken) {
        return null;
    }

    @Override
    public ResponseEntity<byte[]> downloadPaymentAttachment(String id, String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String url = msalesBaseUrl+"/main/downloadAttachment/"+ id;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        HttpEntity<byte[]> requestHttpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, requestHttpEntity, byte[].class);
        return response;
    }
}
