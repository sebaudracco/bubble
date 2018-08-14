package com.vungle.publisher;

import android.content.ContentValues;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class ds {
    cz<?, ?, ?, ?> f2827a;

    @Singleton
    /* compiled from: vungle */
    static class C1600a {
        @Inject
        Provider<ds> f2826a;

        @Inject
        C1600a() {
        }

        public ds m3729a(cz<?, ?, ?, ?> czVar) {
            ds dsVar = (ds) this.f2826a.get();
            dsVar.f2827a = czVar;
            return dsVar;
        }
    }

    @Inject
    ds() {
    }

    public int m3730a() {
        Long l = this.f2827a.f2806o;
        Long l2 = this.f2827a.f2795d;
        String B = this.f2827a.m3575B();
        if (l == null) {
            Logger.w(Logger.REPORT_TAG, "download end millis null for " + B);
            return -1;
        } else if (l.longValue() < 0) {
            return 0;
        } else {
            if (l2 != null) {
                return (int) (l.longValue() - l2.longValue());
            }
            Logger.w(Logger.REPORT_TAG, "insert timestamp millis null for " + B);
            return -1;
        }
    }

    void m3732a(Long l) {
        this.f2827a.f2806o = l;
        Logger.d(Logger.REPORT_TAG, "setting ad download end millis " + l + " (duration " + m3730a() + " ms) for " + this.f2827a.m3575B());
    }

    public void m3733b(Long l) {
        m3732a(l);
        this.f2827a.e_();
    }

    protected ContentValues m3731a(ContentValues contentValues) {
        contentValues.put("download_end_millis", this.f2827a.f2806o);
        return contentValues;
    }
}
