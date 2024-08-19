package com.framework.allure.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public class UserDTO {

    protected StatusEnum status;
    private   Long       id = null;
    private   String     name;
    private   String     email;

    /**
     * Constructor
     */
    public UserDTO() {

    }

    public UserDTO id(final long id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     */
    @JsonProperty("id")
    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public UserDTO name(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public UserDTO email(final String email) {
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

    public UserDTO status(final StatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     */
    @JsonProperty("status")
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(final StatusEnum status) {
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
        UserDTO user = (UserDTO) o;
        return Objects.equals(this.id, user.id) &&
                Objects.equals(this.name, user.name) &&
                Objects.equals(this.email, user.email) &&
                Objects.equals(this.status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, status);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserDTO {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

    /**
     * Gets or Sets status
     */
    public enum StatusEnum {
        A("A"),

        B("B"),

        C("C");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

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
