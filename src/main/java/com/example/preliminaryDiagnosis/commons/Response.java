package com.example.preliminaryDiagnosis.commons;

import lombok.Getter;

@Getter
public class Response<T> {
    boolean status;
    String message;
    T data;

    public Response(final boolean status, final String message) {
        this.status = status;
        this.message = message;
    }

    public Response(final boolean status, final T data) {
        this.status = status;
        this.data = data;
    }
}
