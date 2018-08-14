package com.integralads.avid.library.mopub.session.internal.jsbridge;

import org.json.JSONObject;

public class AvidEvent {
    private JSONObject data;
    private int tag;
    private String type;

    public AvidEvent(int tag, String type, JSONObject data) {
        this.tag = tag;
        this.type = type;
        this.data = data;
    }

    public AvidEvent(int tag, String type) {
        this(tag, type, null);
    }

    public int getTag() {
        return this.tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONObject getData() {
        return this.data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
