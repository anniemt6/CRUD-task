package com.example.userlist.presentation.entity;

public class Response<T> {
    private final T data;
    private final int code;
    private final String message;

    public Response(T data, int code, String error) {
        this.data = data;
        this.code = code;
        this.message = error;
    }

    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}