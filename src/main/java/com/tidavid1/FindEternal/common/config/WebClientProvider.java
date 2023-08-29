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

    public JSONObject requestNicknameAPI(String nickname){
        return webClient.get()
                .uri("/v1/user/nickname?query={nickname}", nickname)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(JSONObject::new)
                .block();
    }

    public JSONObject requestGameDataAPI(Integer gameId){
        return webClient.get()
                .uri("/v1/games/{gameId}", gameId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(JSONObject::new)
                .block();
    }

    public JSONObject requestGameDataAPI(Integer gameId, String next){
        return webClient.get()
                .uri("/v1/games/{gameId}?next={next}", gameId, next)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(JSONObject::new)
                .block();
    }

    public JSONObject requestUserRankAPI(int matchingTeamMode, int seasonId, Integer userNum){
        return webClient.get()
                .uri("/v1/rank/{userNum}/{seasonId}/{matchingTeamMode}", userNum, seasonId, matchingTeamMode)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(JSONObject::new)
                .block();
    }

    public JSONObject requestUserGamesDataAPI(Integer userNum){
        return webClient.get()
                .uri("/v1/user/games/{userNum}", userNum)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(JSONObject::new)
                .block();
    }

    public JSONObject requestUserGamesDataAPI(Integer userNum, String next){
        return webClient.get()
                .uri("/v1/user/games/{userNum}?next={next}", userNum, next)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(JSONObject::new)
                .block();
    }

    public JSONObject requestUserStats(Integer userNum, int seasonId){
        return webClient.get()
                .uri("/v1/user/stats/{userNum}/{seasonId}", userNum, seasonId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(JSONObject::new)
                .block();
    }
}
