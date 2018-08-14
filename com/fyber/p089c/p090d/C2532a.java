package com.fyber.p089c.p090d;

/* compiled from: VideoPlayerEvent */
public enum C2532a {
    PlayingEvent("playing"),
    ErrorEvent("error"),
    TimeUpdateEvent("timeupdate"),
    EndedEvent("ended"),
    ClickThroughEvent("clickThrough"),
    CancelEvent("cancel"),
    TimeoutEvent("timeout");
    
    private final String f6291h;

    private C2532a(String str) {
        this.f6291h = str;
    }

    public final String toString() {
        return this.f6291h;
    }
}
