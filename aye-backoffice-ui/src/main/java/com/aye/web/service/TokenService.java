package com.aye.web.service;

import com.aye.web.model.TokenRequest;
import com.aye.web.model.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${msales.baseurl}")
    private String msalesBaseUrl;

    public TokenResponse getTokens(){

        TokenRequest tokenRequest = setUpTokenRequest();

        String authUrl = msalesBaseUrl + "/auth/authenticate";

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TokenRequest> requestHttpEntity = new HttpEntity<>(tokenRequest, httpHeaders);

        ResponseEntity<TokenResponse> response = restTemplate.exchange(authUrl, HttpMethod.POST, requestHttpEntity, TokenResponse.class);
        return response.getBody();
    }

    private TokenRequest setUpTokenRequest() {
        return new TokenRequest("hafiz", "1234");
    }
}
