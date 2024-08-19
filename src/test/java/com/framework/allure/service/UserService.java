package com.framework.allure.service;

import com.framework.allure.rest.clients.UserClient;
import com.framework.allure.rest.request.CreateUserRequestDTO;
import com.framework.allure.rest.response.CreateUser201ResponseDTO;
import com.framework.allure.rest.response.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger LOG = LogManager.getLogger(UserService.class);

    private final UserClient    userClient;
    private final RandomService randomService;

    public UserService(final UserClient userClient,
                       final RandomService randomService) {
        this.userClient    = userClient;
        this.randomService = randomService;
    }

    public UserDTO initContextUser(final String statusString) {
        UserDTO            user   = new UserDTO();
        UserDTO.StatusEnum status = UserDTO.StatusEnum.valueOf(statusString);
        user.setName(randomService.getRandomString(10));
        user.setEmail(randomService.getRandomString(7) + "@gmail.com");
        user.setStatus(status);

        LOG.debug("User: {}", user);
        return user;
    }

    public ResponseEntity<CreateUser201ResponseDTO> registerUser(final UserDTO user) {
        CreateUserRequestDTO            body   = new CreateUserRequestDTO();
        CreateUserRequestDTO.StatusEnum status = CreateUserRequestDTO.StatusEnum.valueOf(user.getStatus().toString());
        body.setName(user.getName());
        body.setEmail(user.getEmail());
        body.setStatus(status);

        LOG.debug("User create request body: {}", body);
        return userClient.createUser(body);
    }

    public ResponseEntity<List<UserDTO>> getUsers() {
        return userClient.getUsers();
    }


    public ResponseEntity<Void> deleteUser(final Long userId) {
        return userClient.deleteUser(userId);
    }
}
