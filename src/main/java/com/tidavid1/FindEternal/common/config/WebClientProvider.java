package com.tidavid1.FindEternal.common.config;

import jakarta.annotation.PostConstruct;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientProvider {
    private WebClient webClient;

    @Value("${eternal.api.key}")
    private String API_KEY;
    @Value("${eternal.api.url}")
    private String API_URL;

    @PostConstruct
    void init(){
        webClient = WebClient.builder()
                .baseUrl(API_URL)
                .defaultHeaders(headers->{
                    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    headers.add("x-api-key", API_KEY);
                })
                .build();
    }

    // TODO: FIX!
    public String get(){
        JSONObject result = webClient.get()
                .uri("/v1/user/games/146501")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(JSONObject::new)
                .block();
        return result.toString();
    }
}
