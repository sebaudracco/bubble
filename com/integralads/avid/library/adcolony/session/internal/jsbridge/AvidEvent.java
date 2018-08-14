package com.integralads.avid.library.adcolony.session.internal.jsbridge;

import org.json.JSONObject;

public class AvidEvent {
    private int f8377a;
    private String f8378b;
    private JSONObject f8379c;

    public AvidEvent(int tag, String type, JSONObject data) {
        this.f8377a = tag;
        this.f8378b = type;
        this.f8379c = data;
    }

    public AvidEvent(int tag, String type) {
        this(tag, type, null);
    }

    public int getTag() {
        return this.f8377a;
    }

    public void setTag(int tag) {
        this.f8377a = tag;
    }

    public String getType() {
        return this.f8378b;
    }

    public void setType(String type) {
        this.f8378b = type;
    }

    public JSONObject getData() {
        return this.f8379c;
    }

    public void setData(JSONObject data) {
        this.f8379c = data;
    }
}
