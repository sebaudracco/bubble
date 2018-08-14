package com.vungle.publisher;

import com.moat.analytics.mobile.vng.MoatAdEventType;
import com.vungle.publisher.ji.a;
import com.vungle.publisher.log.Logger;
import java.util.HashSet;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class ys extends yj<jh<?, ?, ?>> {
    private static final a[] f3495i = new a[]{a.d, a.e, a.f, a.g, a.h, a.i};
    @Inject
    zf f3496h;
    private int f3497j;
    private final HashSet<a> f3498k = new HashSet();

    @Singleton
    /* compiled from: vungle */
    public static class C1678a {
        @Inject
        ys f3494a;

        @Inject
        C1678a() {
        }

        public ys m4918a(jh<?, ?, ?> jhVar) {
            this.f3494a.m4906a((cn) jhVar);
            return this.f3494a;
        }
    }

    @Inject
    ys() {
    }

    protected void mo3023a() {
        this.f3497j = 0;
        this.f3498k.clear();
    }

    public void onEvent(bd event) {
        float a = event.a();
        if (event.b()) {
            m4908a(jl.a.l, Float.valueOf(a));
        } else {
            m4908a(jl.a.k, Float.valueOf(a));
        }
    }

    public void onEvent(y event) {
        m4907a(jl.a.a);
    }

    public void onEvent(bc event) {
        m4908a(jl.a.j, Float.valueOf(event.a()));
    }

    public void onEvent(bb event) {
        if (event.a()) {
            m4907a(jl.a.f);
            m4919a(a.b);
            return;
        }
        m4907a(jl.a.g);
        m4919a(a.l);
    }

    public void onEvent(ap event) {
        m4919a(a.k);
    }

    public void onEvent(ay event) {
        m4919a(a.n);
        m4907a(jl.a.b);
    }

    public void onEvent(ba event) {
        m4907a(jl.a.i);
    }

    public void onEvent(ab<jh<?, ?, ?>> event) {
        cn cnVar = (jh) event.b();
        this.f.m4674a(cnVar, event.a(), cnVar.m3846z());
    }

    public void onEvent(ah playVideoDurationEvent) {
        try {
            this.c.m3647b(Integer.valueOf(playVideoDurationEvent.a()));
        } catch (Throwable e) {
            Logger.e(Logger.REPORT_TAG, "error updating video duration millis", e);
        }
    }

    public void onEvent(aw playVideoStartEvent) {
        try {
            this.b.a(Long.valueOf(playVideoStartEvent.e()));
            this.b.f_();
        } catch (Throwable e) {
            Logger.e(Logger.REPORT_TAG, "error updating play start millis", e);
        }
    }

    public void onEvent(ar playVideoProgressEvent) {
        Object obj = null;
        try {
            int a = playVideoProgressEvent.a();
            Object obj2 = this.f3497j < f3495i.length ? 1 : null;
            boolean z = playVideoProgressEvent instanceof ai;
            if (obj2 != null || z) {
                Integer s = this.c.m3660s();
                if (s == null) {
                    Logger.d(Logger.REPORT_TAG, "null video duration millis for " + this.c.m3575B());
                } else if (s.intValue() == 0) {
                    Logger.w(Logger.REPORT_TAG, "video duration millis 0 for " + this.c.m3575B());
                } else {
                    if (obj2 != null) {
                        float intValue = ((float) a) / ((float) s.intValue());
                        a aVar = f3495i[this.f3497j];
                        if (intValue >= aVar.b()) {
                            obj = 1;
                        }
                        if (obj != null) {
                            switch (1.a[aVar.ordinal()]) {
                                case 1:
                                    this.f3496h.m4925a(MoatAdEventType.AD_EVT_START, a);
                                    this.eventBus.m4568a(new aw());
                                    break;
                                case 2:
                                    this.f3496h.m4925a(MoatAdEventType.AD_EVT_FIRST_QUARTILE, a);
                                    break;
                                case 3:
                                    this.f3496h.m4925a(MoatAdEventType.AD_EVT_MID_POINT, a);
                                    break;
                                case 4:
                                    this.f3496h.m4925a(MoatAdEventType.AD_EVT_THIRD_QUARTILE, a);
                                    break;
                                case 5:
                                    this.eventBus.m4568a(new ax());
                                    break;
                            }
                            this.f3497j++;
                            m4919a(aVar);
                        }
                    }
                    if (obj != null || z) {
                        m4921a(playVideoProgressEvent);
                    }
                }
            }
        } catch (Throwable e) {
            Logger.e(Logger.REPORT_TAG, "error handling video view progress", e);
        }
    }

    public void onEvent(aa event) {
        a a = event.a();
        if (a == a.m) {
            m4907a(jl.a.e);
        } else if (a == a.j) {
            m4919a(a.j);
        }
        m4907a(jl.a.d);
    }

    public void onEvent(bt endPlayAdEvent) {
        try {
            Logger.d(Logger.REPORT_TAG, "received play ad end");
            m4905a((ac) endPlayAdEvent);
            m4904a(endPlayAdEvent.e());
        } catch (Throwable e) {
            Logger.e(Logger.REPORT_TAG, "error processing ad end", e);
        }
    }

    void m4921a(ar arVar) {
        try {
            this.b.a(Integer.valueOf(arVar.a()));
            this.b.f_();
            this.c.m3648b(Long.valueOf(arVar.e()));
        } catch (Throwable e) {
            Logger.e(Logger.REPORT_TAG, "error updating video view progress", e);
        }
    }

    private void m4919a(a aVar) {
        if (this.a == null) {
            Logger.w(Logger.REPORT_TAG, "null ad in AdReportingHandler - cannot track event " + aVar);
        } else if (!this.f3498k.contains(aVar)) {
            Logger.v(Logger.REPORT_TAG, "tpat event " + aVar.name());
            this.f.m4672a(this.a, (jf) aVar, ((jh) this.a).m3841a((jf) aVar));
            this.f3498k.add(aVar);
        }
    }
}
