package com.aict.loworderbackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AIService {

    @Value("${openai.api.key}")  // application.properties에서 API 키를 불러옴
    private String openaiApiKey;

    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";  // 챗 모델용 엔드포인트

    public String getAiSuggestions(String prompt) {
        // 1. HTTP 요청 헤더에 API 키 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openaiApiKey);
        headers.set("Content-Type", "application/json");

        // 2. 요청 바디 작성 (챗 모델용 형식)
        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("model", "gpt-3.5-turbo");  // 챗 모델 사용
        requestBodyMap.put("messages", List.of(
                Map.of("role", "system", "content", "You are a helpful assistant."),
                Map.of("role", "user", "content", prompt)
        ));
        requestBodyMap.put("max_tokens", 150);

        try {
            // 3. 요청 바디를 JSON으로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBodyJson = objectMapper.writeValueAsString(requestBodyMap);

            // 4. HTTP 요청 생성
            HttpEntity<String> entity = new HttpEntity<>(requestBodyJson, headers);

            // 5. API 호출
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, entity, String.class);

            // 6. API 응답 반환
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            String content = rootNode.path("choices").get(0).path("message").path("content").asText();
            System.out.println(content);
            return content;

        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return "Error occurred during API call";
        }
    }
}