package com.fyber.exceptions;

public class IdException extends IllegalArgumentException {
    private static final long serialVersionUID = 4315205868647624054L;

    public IdException(String str) {
        super(str);
    }
}
