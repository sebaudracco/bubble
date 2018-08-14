package com.facebook.ads.internal;

import android.content.Context;
import android.os.Handler;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAd$MediaCacheFlag;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1894f;
import com.facebook.ads.internal.adapters.ab;
import com.facebook.ads.internal.adapters.ac;
import com.facebook.ads.internal.p056q.p057a.C1866w;
import com.facebook.ads.internal.p056q.p057a.C2110d;
import com.facebook.ads.internal.p056q.p057a.C2120k;
import com.facebook.ads.internal.p056q.p057a.C2123n;
import com.facebook.ads.internal.p056q.p078e.C2151a;
import com.facebook.ads.internal.p058o.C2033b;
import com.facebook.ads.internal.p058o.C2038c;
import com.facebook.ads.internal.p058o.C2038c.C1870a;
import com.facebook.ads.internal.p058o.C2043g;
import com.facebook.ads.internal.p065h.C1987a;
import com.facebook.ads.internal.p065h.C1989c;
import com.facebook.ads.internal.p066i.C1992a;
import com.facebook.ads.internal.p066i.C1995c;
import com.facebook.ads.internal.p068l.C2005a;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.p069m.C2014d;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2097a;
import com.facebook.ads.internal.protocol.C2098b;
import com.facebook.ads.internal.protocol.C2100c;
import com.facebook.ads.internal.protocol.C2101d;
import com.facebook.ads.internal.protocol.C2105h;
import com.facebook.ads.internal.protocol.f;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.altbeacon.beacon.service.scanner.CycledLeScanner;

public class C1883a implements C1870a {
    private final Context f4149a;
    private final String f4150b;
    private final C2038c f4151c;
    private final f f4152d;
    private final C2101d f4153e;
    private final AdSize f4154f;
    private final int f4155g;
    private boolean f4156h = true;
    private final Handler f4157i = new Handler();
    private final Runnable f4158j = new C1872b(this);
    private final C2012c f4159k;
    private C1852a f4160l;
    private C1989c f4161m;

    public interface C1852a {
        void mo3589a(C2097a c2097a);

        void mo3590a(List<ab> list);
    }

    private static final class C1872b extends C1866w<C1883a> {
        public C1872b(C1883a c1883a) {
            super(c1883a);
        }

        public void run() {
            C1883a c1883a = (C1883a) m5564a();
            if (c1883a != null) {
                if (C2151a.m6889a(c1883a.f4149a)) {
                    c1883a.m5664a();
                } else {
                    c1883a.f4157i.postDelayed(c1883a.f4158j, 5000);
                }
            }
        }
    }

    static {
        C2110d.m6771a();
    }

    public C1883a(Context context, String str, f fVar, AdSize adSize, C2101d c2101d, int i, EnumSet<NativeAd$MediaCacheFlag> enumSet) {
        this.f4149a = context;
        this.f4150b = str;
        this.f4152d = fVar;
        this.f4154f = adSize;
        this.f4153e = c2101d;
        this.f4155g = i;
        this.f4151c = new C2038c(context);
        this.f4151c.m6538a((C1870a) this);
        this.f4159k = C2014d.m6413a(context);
        C1992a.m6302a(context).m6303a();
    }

    private List<ab> m5663d() {
        C1989c c1989c = this.f4161m;
        C1987a d = c1989c.m6290d();
        final List<ab> arrayList = new ArrayList(c1989c.m6289c());
        for (C1987a c1987a = d; c1987a != null; c1987a = c1989c.m6290d()) {
            AdAdapter a = C1894f.m5818a(c1987a.m6280a(), AdPlacementType.NATIVE);
            if (a != null && a.getPlacementType() == AdPlacementType.NATIVE) {
                Map hashMap = new HashMap();
                hashMap.put("data", c1987a.m6282b());
                hashMap.put("definition", c1989c.m6286a());
                ((ab) a).mo3636a(this.f4149a, new ac(this) {
                    final /* synthetic */ C1883a f4123b;

                    public void mo3601a(ab abVar) {
                        arrayList.add(abVar);
                    }

                    public void mo3602a(ab abVar, C2097a c2097a) {
                    }

                    public void mo3603b(ab abVar) {
                    }

                    public void mo3604c(ab abVar) {
                    }
                }, this.f4159k, hashMap, NativeAd.getViewTraversalPredicate());
            }
        }
        return arrayList;
    }

    public void m5664a() {
        try {
            this.f4151c.m6537a(new C2033b(this.f4149a, new C1995c(this.f4149a, false), this.f4150b, this.f4154f != null ? new C2120k(this.f4154f.getHeight(), this.f4154f.getWidth()) : null, this.f4152d, this.f4153e, null, C1894f.m5819a(C2100c.m6752a(this.f4152d).m6753a()), this.f4155g, AdSettings.isTestMode(this.f4149a), AdSettings.isChildDirected(), new C2105h(this.f4149a, null, null, null), C2123n.m6808a(C2005a.m6352m(this.f4149a))));
        } catch (C2098b e) {
            mo3620a(C2097a.m6747a(e));
        }
    }

    public void m5665a(C1852a c1852a) {
        this.f4160l = c1852a;
    }

    public void mo3619a(C2043g c2043g) {
        C1989c a = c2043g.mo3732a();
        if (a == null) {
            throw new IllegalStateException("no placement in response");
        }
        if (this.f4156h) {
            long c = a.m6286a().m6294c();
            if (c == 0) {
                c = CycledLeScanner.ANDROID_N_MAX_SCAN_DURATION_MILLIS;
            }
            this.f4157i.postDelayed(this.f4158j, c);
        }
        this.f4161m = a;
        List d = m5663d();
        if (this.f4160l == null) {
            return;
        }
        if (d.isEmpty()) {
            this.f4160l.mo3589a(C2097a.m6746a(AdErrorType.NO_FILL, ""));
        } else {
            this.f4160l.mo3590a(d);
        }
    }

    public void mo3620a(C2097a c2097a) {
        if (this.f4156h) {
            this.f4157i.postDelayed(this.f4158j, CycledLeScanner.ANDROID_N_MAX_SCAN_DURATION_MILLIS);
        }
        if (this.f4160l != null) {
            this.f4160l.mo3589a(c2097a);
        }
    }

    public void m5668b() {
    }

    public void m5669c() {
        this.f4156h = false;
        this.f4157i.removeCallbacks(this.f4158j);
    }
}
