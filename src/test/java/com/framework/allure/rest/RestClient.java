package com.framework.allure.rest;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;

import java.util.List;


public class RestClient {
    private static final Logger LOG = LogManager.getLogger(RestClient.class);
    private final WebClient webClient;
    private final HttpHeaders headers = new HttpHeaders();
    private final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
    private final String url;

    public RestClient(final String url,
                      final HttpHeaders headers) {
        headers.forEach(this.headers::addAll);
        this.url = url;
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeaders(httpHeaders -> httpHeaders.addAll(this.headers)).build();
        LOG.trace(webClient);
    }

    public <T> ResponseEntity<List<T>> getList(final String endpoint, final Class<T> clazz) {
        logCurl("GET", endpoint, null);

        ResponseEntity<List<T>> response = webClient
                .get()
                .uri(endpoint)
                .exchangeToMono(clientResponse -> clientResponse.toEntityList(clazz))
                .block();

        LOG.debug("Response: {}", response);
        return response;
    }

    public <T> ResponseEntity<T> get(final String endpoint, final Class<T> clazz) {
        logCurl("GET", endpoint, null);

        ResponseEntity<T> response = webClient
                .get()
                .uri(endpoint)
                .exchangeToMono(clientResponse -> clientResponse.toEntity(clazz))
                .block();

        LOG.debug("Response: {}", response);
        return response;
    }

    public <T> ResponseEntity<T> post(final String endpoint, final Object body, final Class<T> clazz) {
        logCurl("POST", endpoint, body);

        ResponseEntity<T> response = webClient
                .post()
                .uri(endpoint)
                .bodyValue(body)
                .exchangeToMono(clientResponse -> clientResponse.toEntity(clazz))
                .block();

        LOG.debug("Response: {}", response);
        return response;
    }

    public <T> ResponseEntity<T> delete(final String endpoint, final Class<T> clazz) {
        logCurl("DELETE", endpoint, null);

        ResponseEntity<T> response = webClient
                .delete()
                .uri(endpoint)
                .exchangeToMono(clientResponse -> clientResponse.toEntity(clazz))
                .block();

        LOG.debug("Response: {}", response);
        return response;
    }

    private void logCurl(final String requestType, final String endpoint, final Object body) {
        StringBuilder curlCommand = new StringBuilder("curl -X ").append(requestType).append(" ");

        headers.forEach((key, value) ->
                value.forEach(val -> curlCommand
                        .append("-H \"")
                        .append(key)
                        .append(": ")
                        .append(val)
                        .append("\" ")));

        curlCommand.append(url).append(endpoint).append(" ");

        if (body != null) {
            try {
                String jsonPayload = mapper.writeValueAsString(body);
                curlCommand.append("-d '").append(jsonPayload).append("' ");
            } catch (JsonProcessingException e) {
                LOG.debug("Failed to convert payload to JSON", e);
            }
        }

        LOG.info("Curl command: " + curlCommand.toString().trim());
    }
}
