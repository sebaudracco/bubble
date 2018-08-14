package com.cuebiq.cuebiqsdk.model.wrapper;

import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;

public class LogAppBean {
    private String eventType;
    private String memberId;
    private String message;
    private String payload;
    private String stackTrace;
    private String statusCode;

    public String getEventType() {
        return this.eventType;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPayload() {
        return this.payload;
    }

    public String getStackTrace() {
        return this.stackTrace;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setEventType(String str) {
        this.eventType = str;
    }

    public void setMemberId(String str) {
        this.memberId = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setPayload(String str) {
        this.payload = str;
    }

    public void setStackTrace(String str) {
        this.stackTrace = str;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public String toString() {
        return CuebiqSDKImpl.GSON.toJson((Object) this);
    }
}
