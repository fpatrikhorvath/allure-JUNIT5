package com.framework.allure.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CreateBookForUserRequestDTO {
    private String title;
    private String author;

    public CreateBookForUserRequestDTO() {
    }

    public CreateBookForUserRequestDTO title(final String title) {
        this.title = title;
        return this;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public CreateBookForUserRequestDTO author(final String author) {
        this.author = author;
        return this;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateBookForUserRequestDTO createBookForUserRequest = (CreateBookForUserRequestDTO) o;
        return Objects.equals(this.title, createBookForUserRequest.title) &&
                Objects.equals(this.author, createBookForUserRequest.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateBookForUserRequestDTO {\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    author: ").append(toIndentedString(author)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

