package com.appsgeyser.sdk;

public class ErrorInfo {
    private int code;
    private final String message;

    public ErrorInfo(String msg) {
        this.message = msg;
    }

    public ErrorInfo(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }
}
