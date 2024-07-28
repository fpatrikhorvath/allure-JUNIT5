package com.framework.allure.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class BookDTO {

    private Long id = null;

    private Long userId = null;

    private String title;

    private String author;

    public BookDTO() {

    }

    public BookDTO id(final long id) {
        this.id = id;
        return this;
    }


    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public BookDTO userId(final long userId) {
        this.userId = userId;
        return this;
    }


    @JsonProperty("userId")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(final long userId) {
        this.userId = userId;
    }

    public BookDTO title(final String title) {
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

    public BookDTO author(final String author) {
        this.author = author;
        return this;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
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
        BookDTO book = (BookDTO) o;
        return Objects.equals(this.id, book.id) &&
                Objects.equals(this.userId, book.userId) &&
                Objects.equals(this.title, book.title) &&
                Objects.equals(this.author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, author);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BookDTO {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    author: ").append(toIndentedString(author)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(final Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

