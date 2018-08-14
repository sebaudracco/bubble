package com.integralads.avid.library.mopub.session;

public class ExternalAvidAdSessionContext {
    private boolean isDeferred;
    private String partnerVersion;

    public ExternalAvidAdSessionContext(String partnerVersion) {
        this(partnerVersion, false);
    }

    public ExternalAvidAdSessionContext(String partnerVersion, boolean isDeferred) {
        this.partnerVersion = partnerVersion;
        this.isDeferred = isDeferred;
    }

    public String getPartnerVersion() {
        return this.partnerVersion;
    }

    public boolean isDeferred() {
        return this.isDeferred;
    }
}
