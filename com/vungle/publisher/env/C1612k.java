package com.vungle.publisher.env;

import android.content.SharedPreferences;
import com.vungle.publisher.bh;
import com.vungle.publisher.bj;
import com.vungle.publisher.bp;
import com.vungle.publisher.bs;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.pi;
import com.vungle.publisher.zl;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class C1612k extends pi {
    @Inject
    SharedPreferences f2902a;
    AtomicReference<String> f2903b = new AtomicReference(null);
    private long f2904c;

    @Inject
    C1612k() {
    }

    public boolean m3908a() {
        return this.f2903b.get() != null;
    }

    public boolean m3909a(String str) {
        if (!m3910b()) {
            this.eventBus.m4568a(new bp(null, str, (int) ((zl.m4930b() - m3911c()) / 1000), m3913e()));
            return false;
        } else if (this.f2903b.compareAndSet(null, str)) {
            register();
            return true;
        } else {
            Logger.d(Logger.AD_TAG, "ad already playing for placement: " + this.f2903b);
            this.eventBus.m4568a(new bj(str));
            return false;
        }
    }

    public boolean m3910b() {
        boolean z = true;
        long b = zl.m4930b();
        long c = m3911c();
        int b2 = (int) ((zl.m4930b() - m3911c()) / 1000);
        if (b2 < 0) {
            Logger.d(Logger.AD_TAG, "negative adDelayElapsedSeconds " + b2 + ", currentTimestampMillis " + b + ", lastAdEndMillis " + c);
        } else {
            int e = m3913e();
            if (b2 < e) {
                z = false;
            }
            if (z) {
                Logger.v(Logger.AD_TAG, b2 + " / " + e + " ad delay seconds elapsed");
            } else {
                Logger.d(Logger.AD_TAG, b2 + " / " + e + " ad delay seconds elapsed");
            }
        }
        return z;
    }

    public long m3911c() {
        long j = this.f2902a.getLong("VgLastViewedTime", 0);
        Logger.v(Logger.AD_TAG, "returning last ad end millis: " + j);
        return j;
    }

    void m3912d() {
        long b = zl.m4930b();
        Logger.v(Logger.AD_TAG, "setting last ad end millis: " + b);
        this.f2902a.edit().putLong("VgLastViewedTime", b).apply();
    }

    public int m3913e() {
        return this.f2902a.getInt("VgAdDelayDuration", 0);
    }

    public void m3906a(Integer num) {
        if (num == null) {
            Logger.v(Logger.AD_TAG, "ignoring set null min ad delay seconds");
        } else {
            m3904a(num.intValue());
        }
    }

    public void m3904a(int i) {
        Logger.d(Logger.AD_TAG, "setting min ad delay seconds: " + i);
        this.f2902a.edit().putInt("VgAdDelayDuration", i).apply();
    }

    public void m3907a(boolean z) {
        String str = (String) this.f2903b.get();
        if (str != null && this.f2903b.compareAndSet(str, null)) {
            Logger.d(Logger.AD_TAG, "ending playing ad. isNormalAdEnd? " + z);
            unregister();
            m3912d();
            m3914f();
            if (!z) {
                this.eventBus.m4568a(new bs(null, str, this.f2904c));
            }
        }
    }

    void m3914f() {
        this.f2904c = 0;
    }

    void m3905a(long j) {
        this.f2904c = j;
    }

    public void onEvent(bh endAdEvent) {
        Logger.d(Logger.AD_TAG, "InterstitialAdState received end ad event");
        m3907a(true);
    }
}
