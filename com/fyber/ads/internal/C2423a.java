package com.fyber.ads.internal;

import android.support.v4.app.NotificationCompat;

/* compiled from: AdEvent */
public enum C2423a {
    ValidationRequest("request"),
    ValidationFill("fill"),
    ValidationNoFill("no_fill"),
    ValidationError("error"),
    ValidationTimeout("timeout"),
    ShowImpression("impression"),
    ShowRotation("rotation"),
    ShowClick("click"),
    ShowClose("close"),
    ShowError("error"),
    NotIntegrated("no_sdk"),
    Progress(NotificationCompat.CATEGORY_PROGRESS),
    Interaction("interaction");
    
    private final String f6060n;

    private C2423a(String str) {
        this.f6060n = str;
    }

    public final String toString() {
        return this.f6060n;
    }
}
