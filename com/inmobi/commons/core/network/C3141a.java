package com.inmobi.commons.core.network;

import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: AsyncNetworkTask */
public final class C3141a {
    private static final String f7746a = C3141a.class.getSimpleName();
    private NetworkRequest f7747b;
    private C3048a f7748c;

    /* compiled from: AsyncNetworkTask */
    public interface C3048a {
        void mo6236a(C3143c c3143c);

        void mo6237b(C3143c c3143c);
    }

    /* compiled from: AsyncNetworkTask */
    class C31401 implements Runnable {
        final /* synthetic */ C3141a f7745a;

        C31401(C3141a c3141a) {
            this.f7745a = c3141a;
        }

        public void run() {
            try {
                C3143c a = new C3142b(this.f7745a.f7747b).m10346a();
                if (a.m10351a()) {
                    this.f7745a.f7748c.mo6237b(a);
                } else {
                    this.f7745a.f7748c.mo6236a(a);
                }
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3141a.f7746a, "Network request failed with unexpected error: " + e.getMessage());
                NetworkError networkError = new NetworkError(ErrorCode.UNKNOWN_ERROR, "Network request failed with unknown error");
                C3143c c3143c = new C3143c(this.f7745a.f7747b);
                c3143c.m10347a(networkError);
                this.f7745a.f7748c.mo6237b(c3143c);
            }
        }
    }

    public C3141a(NetworkRequest networkRequest, C3048a c3048a) {
        this.f7747b = networkRequest;
        this.f7748c = c3048a;
    }

    public void m10339a() {
        new Thread(new C31401(this)).start();
    }
}
