package com.framework.allure.rest.clients;

import com.framework.allure.config.UserLayerConfig;
import com.framework.allure.rest.RestClient;
import com.framework.allure.rest.request.CreateBookForUserRequestDTO;
import com.framework.allure.rest.response.BookDTO;
import com.framework.allure.rest.response.GenericErrorResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import org.springframework.http.HttpHeaders;
import java.util.List;

@Service
public class BookClient {
    private static final String GET_BOOK_PATH = "/users/{userId}/books";
    private static final String POST_BOOK_PATH = "/users/{userId}/books";
    private static final String DELETE_BOOK_PATH = "/users/{userId}/books/{bookId}";
    private final UserLayerConfig userLayerConfig;
    private final RestClient restClient;

    public BookClient(final UserLayerConfig userLayerConfig) {
        this.userLayerConfig = userLayerConfig;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        this.restClient = new RestClient(this.userLayerConfig.getUrl(), headers);
    }

    public ResponseEntity<BookDTO> createBookForUser(final Long userId, final CreateBookForUserRequestDTO body) {
        String endpoint = StringUtils.replace(POST_BOOK_PATH, "{userId}", String.valueOf(userId));
        return restClient.post(endpoint, body, BookDTO.class);
    }


    public ResponseEntity<GenericErrorResponse> createBookForUserNeg(final Long userId, final CreateBookForUserRequestDTO body) {
        String endpoint = StringUtils.replace(POST_BOOK_PATH, "{userId}", String.valueOf(userId));
        return restClient.post(endpoint, body, GenericErrorResponse.class);
    }

    public ResponseEntity<Void> deleteBookForUser(final Long userId, final Long bookId) {
        String endpoint = StringUtils
                .replace(DELETE_BOOK_PATH, "{userId}", String.valueOf(userId))
                .replace("{bookId}", String.valueOf(bookId));
        return restClient.delete(endpoint, Void.class);
    }

    public ResponseEntity<GenericErrorResponse> deleteBookForUserNeg(final Long userId, final Long bookId) {
        String endpoint = StringUtils
                .replace(DELETE_BOOK_PATH, "{userId}", String.valueOf(userId))
                .replace("{bookId}", String.valueOf(bookId));
        return restClient.delete(endpoint, GenericErrorResponse.class);
    }

    public ResponseEntity<List<BookDTO>> getBooksForUser(final Long userId) {
        String endpoint = StringUtils.replace(GET_BOOK_PATH, "{userId}", String.valueOf(userId));
        return restClient.getList(endpoint, BookDTO.class);
    }

    public ResponseEntity<GenericErrorResponse> getBooksForUserNeg(final Long userId) {
        String endpoint = StringUtils.replace(GET_BOOK_PATH, "{userId}", String.valueOf(userId));
        return restClient.get(endpoint, GenericErrorResponse.class);
    }
}
