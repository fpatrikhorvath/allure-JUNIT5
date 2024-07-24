package com.framework.allure.rest.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public class CreateUserRequestDTO {

    protected String name;

    protected String email;
    protected StatusEnum status;

    public CreateUserRequestDTO() {

    }

    public CreateUserRequestDTO name(final String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreateUserRequestDTO email(final String email) {
        this.email = email;
        return this;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public CreateUserRequestDTO status(final StatusEnum status) {
        this.status = status;
        return this;
    }

    @JsonProperty("status")
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateUserRequestDTO createUserRequest = (CreateUserRequestDTO) o;
        return Objects.equals(this.name, createUserRequest.name) &&
                Objects.equals(this.email, createUserRequest.email) &&
                Objects.equals(this.status, createUserRequest.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, status);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateUserRequestDTO {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(final Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public enum StatusEnum {
        A("A"),

        B("B"),

        C("C");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static StatusEnum fromValue(String value) {
            for (StatusEnum b : StatusEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}

