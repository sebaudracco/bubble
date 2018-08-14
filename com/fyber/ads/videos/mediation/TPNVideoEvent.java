package com.fyber.ads.videos.mediation;

public enum TPNVideoEvent {
    Started("started"),
    Aborted("aborted"),
    Finished("finished"),
    Closed("closed"),
    NoVideo("no_video"),
    Timeout("timeout"),
    Error("error"),
    AdapterNotIntegrated("no_sdk");
    
    private final String text;

    private TPNVideoEvent(String str) {
        this.text = str;
    }

    public final String toString() {
        return this.text;
    }
}
