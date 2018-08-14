package com.integralads.avid.library.adcolony.session;

public class ExternalAvidAdSessionContext {
    private String f8342a;
    private boolean f8343b;

    public ExternalAvidAdSessionContext(String partnerVersion) {
        this(partnerVersion, false);
    }

    public ExternalAvidAdSessionContext(String partnerVersion, boolean isDeferred) {
        this.f8342a = partnerVersion;
        this.f8343b = isDeferred;
    }

    public String getPartnerVersion() {
        return this.f8342a;
    }

    public boolean isDeferred() {
        return this.f8343b;
    }
}
