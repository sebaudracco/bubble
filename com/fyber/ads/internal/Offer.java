package com.fyber.ads.internal;

import android.support.annotation.NonNull;
import com.fyber.mediation.p108b.C2580a;

public class Offer {
    private final String f6041a;
    private final String f6042b;
    private String f6043c;
    private final String f6044d;
    private C2580a f6045e;

    public Offer(String str, String str2, String str3) {
        this.f6041a = str;
        this.f6042b = str2;
        this.f6044d = str3;
    }

    public Offer setPlacementId(String str) {
        this.f6043c = str;
        return this;
    }

    public String getAdId() {
        return this.f6042b;
    }

    public String getProviderType() {
        return this.f6041a;
    }

    public String getPlacementId() {
        return this.f6043c;
    }

    public String getRequestId() {
        return this.f6044d;
    }

    @NonNull
    public C2580a getProviderRequest() {
        if (this.f6045e == null) {
            this.f6045e = new C2580a();
        }
        return this.f6045e;
    }

    public void setProviderRequest(C2580a c2580a) {
        this.f6045e = c2580a;
    }

    public int getProviderStatus() {
        return ((Integer) getProviderRequest().mo3971a("PROVIDER_STATUS", Integer.class, Integer.valueOf(-1))).intValue();
    }

    public void setProviderStatus(int i) {
        getProviderRequest().m8228a("PROVIDER_STATUS", Integer.valueOf(i));
    }
}
