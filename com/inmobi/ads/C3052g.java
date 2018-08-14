package com.inmobi.ads;

import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.network.C3143c;
import com.inmobi.commons.core.network.NetworkError;

/* compiled from: AdNetworkResponse */
final class C3052g {
    private C3143c f7429a;
    private InMobiAdRequestStatus f7430b;
    private C3050f f7431c;

    public C3052g(C3050f c3050f, C3143c c3143c) {
        this.f7431c = c3050f;
        this.f7429a = c3143c;
        if (this.f7429a.m10355e() != null) {
            m9787e();
        }
    }

    private void m9787e() {
        switch (this.f7429a.m10355e().m10332a()) {
            case NETWORK_UNAVAILABLE_ERROR:
                this.f7430b = new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE);
                return;
            case BAD_REQUEST:
                this.f7430b = new InMobiAdRequestStatus(StatusCode.REQUEST_INVALID);
                if (this.f7429a.m10355e().m10333b() != null) {
                    this.f7430b.setCustomMessage(this.f7429a.m10355e().m10333b());
                    return;
                }
                return;
            case HTTP_GATEWAY_TIMEOUT:
                this.f7430b = new InMobiAdRequestStatus(StatusCode.REQUEST_TIMED_OUT);
                return;
            case HTTP_INTERNAL_SERVER_ERROR:
            case HTTP_NOT_IMPLEMENTED:
            case HTTP_BAD_GATEWAY:
            case HTTP_SERVER_NOT_AVAILABLE:
            case HTTP_VERSION_NOT_SUPPORTED:
                this.f7430b = new InMobiAdRequestStatus(StatusCode.SERVER_ERROR);
                return;
            default:
                this.f7430b = new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR);
                return;
        }
    }

    public InMobiAdRequestStatus m9788a() {
        return this.f7430b;
    }

    public C3050f m9789b() {
        return this.f7431c;
    }

    public String m9790c() {
        return this.f7429a.m10352b();
    }

    public NetworkError m9791d() {
        return this.f7429a.m10355e();
    }
}
