package com.rentcar.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.model-name}")
    private String modelName;

    private final RestTemplate restTemplate;

    public GeminiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateContent(String prompt, byte[] imageBytes) throws IOException {
        String apiUrl = String.format("https://generativelanguage.googleapis.com/v1beta/models/%s:generateContent?key=%s", modelName, apiKey);

        // Convert imageBytes to Base64
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        // Create the image part
        JSONObject imagePart = new JSONObject();
        imagePart.put("inlineData", new JSONObject()
                .put("mimeType", "image/jpeg")
                .put("data", base64Image));

        // Create the text part
        JSONObject textPart = new JSONObject();
        textPart.put("text", prompt);

        // Create the parts array
        JSONArray parts = new JSONArray();
        parts.put(imagePart);
        parts.put(textPart);

        JSONObject content = new JSONObject();
        content.put("parts", parts);

        JSONObject requestBody = new JSONObject();
        requestBody.put("contents", new JSONArray().put(content));

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        // Send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            JSONObject jsonResponse = new JSONObject(response.getBody());
            // Extract the text from the response
            return jsonResponse.getJSONArray("candidates")
                    .getJSONObject(0)
                    .getJSONObject("content")
                    .getJSONArray("parts")
                    .getJSONObject(0)
                    .getString("text");
        } else {
            throw new IOException("Gemini API request failed with status: " + response.getStatusCode() + " and body: " + response.getBody());
        }
    }
}
