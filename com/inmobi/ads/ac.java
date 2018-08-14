package com.inmobi.ads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.AdUnit.C2905b;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.squareup.picasso.Picasso;
import java.util.HashMap;
import java.util.Map;

/* compiled from: NativeStrandAdUnit */
final class ac extends AdUnit {
    private static final String f7018b = ac.class.getSimpleName();
    private static Handler f7019f;
    @NonNull
    private final String f7020c;
    private boolean f7021d = false;
    private ai f7022e;

    public ac(@NonNull Context context, long j, Integer[] numArr, C2905b c2905b) {
        super(context, j, c2905b);
        this.f7020c = m9226a(numArr);
        f7019f = new Handler(Looper.getMainLooper());
    }

    public boolean mo6142S() {
        return this.f7021d;
    }

    public void mo6139A() {
        if (this.f7021d) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7018b, "Ad unit is already destroyed! Returning ...");
            return;
        }
        try {
            RecyclerView.class.getName();
            Picasso.class.getName();
            Logger.m10359a(InternalLogLevel.DEBUG, f7018b, "Fetching a Native Strands ad for placement id: " + m8736b());
            super.mo6139A();
        } catch (NoClassDefFoundError e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7018b, "Some of the dependency libraries for InMobiNativeStrand not found");
            m8746b("MissingDependency");
            C2905b q = m8776q();
            if (q != null) {
                q.mo6121a((AdUnit) this, new InMobiAdRequestStatus(StatusCode.MISSING_REQUIRED_DEPENDENCIES));
            }
        }
    }

    protected AdContainer mo6167t() {
        return this.f7022e;
    }

    protected boolean mo6140F() {
        if (AdState.STATE_LOADING != m8748c()) {
            return false;
        }
        m8724a(new InMobiAdRequestStatus(StatusCode.REQUEST_PENDING), false);
        Logger.m10359a(InternalLogLevel.ERROR, f7018b, "An ad load is already in progress. Please wait for the load to complete before requesting for another ad");
        return true;
    }

    protected void mo6150b(C2968a c2968a) {
    }

    public void mo6149b(long j, @NonNull C2968a c2968a) {
        try {
            Logger.m10359a(InternalLogLevel.DEBUG, f7018b, "Native Strands ad successfully fetched for placement id: " + m8736b());
            if (m8748c() != AdState.STATE_LOADING) {
                return;
            }
            if (mo6148a(c2968a)) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7018b, "Ad fetch successful");
                m8720a(AdState.STATE_AVAILABLE);
                m8750c(c2968a);
                if (m8776q() != null) {
                    m8776q().mo6122a((AdUnit) this, true);
                    return;
                }
                return;
            }
            m8752c("ParsingFailed");
            m8724a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), true);
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Failed to load ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, f7018b, "SDK encountered unexpected error in loading ad; " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
        }
    }

    protected void mo6164b(long j, boolean z) {
        super.mo6164b(j, z);
        if (z) {
            if (j == m8736b() && AdState.STATE_AVAILABLE == m8748c()) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7018b, "Setting state to READY");
                m8720a(AdState.STATE_READY);
                if (m8776q() != null) {
                    m8776q().mo6120a((AdUnit) this);
                }
            }
        } else if (j != m8736b()) {
        } else {
            if (AdState.STATE_AVAILABLE == m8748c() || AdState.STATE_READY == m8748c()) {
                m8720a(AdState.STATE_CREATED);
                if (m8776q() != null) {
                    m8776q().mo6121a((AdUnit) this, new InMobiAdRequestStatus(StatusCode.AD_NO_LONGER_AVAILABLE));
                }
            }
        }
    }

    public void mo6143T() {
        try {
            if (!this.f7021d) {
                super.mo6141J();
                m8720a(AdState.STATE_CREATED);
                this.f7021d = true;
            }
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Could not destroy native ad; SDK encountered unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, f7018b, "SDK encountered unexpected error in destroying native ad unit; " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
        }
    }

    protected String mo6151d() {
        return "inlban";
    }

    protected String mo6152f() {
        return null;
    }

    protected PlacementType mo6153h() {
        return PlacementType.PLACEMENT_TYPE_INLINE;
    }

    protected Map<String, String> mo6166i() {
        Map hashMap = new HashMap();
        hashMap.put("a-adPositions", this.f7020c);
        hashMap.put("a-parentViewWidth", String.valueOf(m9223X()));
        hashMap.put("a-productVersion", m9224Y());
        hashMap.put("trackerType", "url_ping");
        return hashMap;
    }

    protected void mo6141J() {
        m8774o();
    }

    private static String m9226a(Integer[] numArr) {
        if (numArr.length == 0) {
            return "";
        }
        String str = "";
        for (Integer intValue : numArr) {
            str = str + intValue.intValue() + ",";
        }
        return str.substring(0, str.length() - 1);
    }

    private int m9223X() {
        return DisplayInfo.m10420a().m10439b();
    }

    private String m9224Y() {
        return "NS-1.0.0-20160411";
    }

    void m9235a(@NonNull final ai aiVar) {
        f7019f.post(new Runnable(this) {
            final /* synthetic */ ac f7015b;

            public void run() {
                try {
                    if (!this.f7015b.f7021d) {
                        this.f7015b.f7022e = aiVar;
                        Map hashMap = new HashMap();
                        hashMap.put("loadLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.f7015b.a));
                        this.f7015b.m8753c("ads", "AdLoadSuccessful", hashMap);
                        if (this.f7015b.m8776q() != null) {
                            this.f7015b.m8776q().mo6119a();
                        }
                    }
                } catch (Throwable e) {
                    Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Failed to load ad; SDK encountered an unexpected error");
                    Logger.m10359a(InternalLogLevel.INTERNAL, ac.f7018b, "SDK encountered unexpected error in handling data model validation success; " + e.getMessage());
                    C3135c.m10255a().m10279a(new C3132b(e));
                }
            }
        });
    }

    void mo6165g(@NonNull final String str) {
        f7019f.post(new Runnable(this) {
            final /* synthetic */ ac f7017b;

            public void run() {
                try {
                    if (!this.f7017b.f7021d) {
                        this.f7017b.m8720a(AdState.STATE_FAILED);
                        this.f7017b.m8752c(str);
                        if (this.f7017b.m8776q() != null) {
                            this.f7017b.m8776q().mo6121a(this.f7017b, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                        }
                    }
                } catch (Throwable e) {
                    Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Failed to load ad; SDK encountered an unexpected error");
                    Logger.m10359a(InternalLogLevel.INTERNAL, ac.f7018b, "SDK encountered unexpected error in handling data model validation failure; " + e.getMessage());
                    C3135c.m10255a().m10279a(new C3132b(e));
                }
            }
        });
    }

    public void mo6144U() {
        if (m8748c() == AdState.STATE_READY && this.f7022e != null) {
            this.f7022e.m9350v();
        }
    }

    public void mo6145V() {
        if (m8748c() == AdState.STATE_READY && this.f7022e != null) {
            this.f7022e.m9349u();
        }
    }
}
