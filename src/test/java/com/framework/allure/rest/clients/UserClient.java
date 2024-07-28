package com.framework.allure.rest.clients;


import com.framework.allure.config.UserLayerConfig;
import com.framework.allure.rest.RestClient;
import com.framework.allure.rest.request.CreateUserRequestDTO;
import com.framework.allure.rest.response.CreateUser201ResponseDTO;
import com.framework.allure.rest.response.GenericErrorResponse;
import com.framework.allure.rest.response.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserClient {
    private static final String CREATE_USER_PATH = "/users";
    private static final String GET_USER_PATH = "/users";
    private static final String DELETE_USER_PATH = "/users/{userId}";

    private final UserLayerConfig userLayerConfig;
    private final RestClient restClient;

    public UserClient(final UserLayerConfig userLayerConfig) {
        this.userLayerConfig = userLayerConfig;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        this.restClient = new RestClient(userLayerConfig.getUrl(), headers);
    }

    public ResponseEntity<CreateUser201ResponseDTO> createUser(final CreateUserRequestDTO body) {
        return restClient.post(CREATE_USER_PATH, body, CreateUser201ResponseDTO.class);
    }


    public ResponseEntity<GenericErrorResponse> createUserNeg(final CreateUserRequestDTO body) {
        return restClient.post(CREATE_USER_PATH, body, GenericErrorResponse.class);
    }

    public ResponseEntity<Void> deleteUser(final Long userId) {
        String endpoint = StringUtils.replace(DELETE_USER_PATH, "{userId}", String.valueOf(userId));
        return restClient.delete(endpoint, Void.class);
    }

    public ResponseEntity<GenericErrorResponse> deleteUserNeg(final Long userId) {
        String endpoint = StringUtils.replace(DELETE_USER_PATH, "{userId}", String.valueOf(userId));
        return restClient.delete(endpoint, GenericErrorResponse.class);
    }

    public ResponseEntity<List<UserDTO>> getUsers() {
        return restClient.getList(GET_USER_PATH, UserDTO.class);
    }

    public ResponseEntity<GenericErrorResponse> getUsersNeg() {
        return restClient.get(GET_USER_PATH, GenericErrorResponse.class);
    }
}
