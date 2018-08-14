package com.inmobi.commons.core.configs;

class ConfigError {
    private ErrorCode f7631a;
    private String f7632b;

    public enum ErrorCode {
        NETWORK_ERROR,
        CONFIG_SERVER_INTERNAL_ERROR,
        PARSING_ERROR
    }

    public ConfigError(ErrorCode errorCode, String str) {
        this.f7631a = errorCode;
        this.f7632b = str;
    }

    public ErrorCode m10153a() {
        return this.f7631a;
    }

    public String m10154b() {
        return this.f7632b;
    }
}
