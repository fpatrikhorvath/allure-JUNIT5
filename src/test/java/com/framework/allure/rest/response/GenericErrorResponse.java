package com.framework.allure.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericErrorResponse {
    @JsonProperty("error")
    private String error;

    public GenericErrorResponse() {
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GenericErrorResponse{");
        sb.append("error='").append(error).append('\'');
        sb.append('}');
        return sb.toString();
    }
}