package com.appsgeyser.sdk.vast.model;

public class Tracking {
    private TRACKING_EVENTS_TYPE event;
    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TRACKING_EVENTS_TYPE getEvent() {
        return this.event;
    }

    public void setEvent(TRACKING_EVENTS_TYPE event) {
        this.event = event;
    }

    public String toString() {
        return "Tracking [event=" + this.event + ", value=" + this.value + "]";
    }
}
