package com.vungle.publisher;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings.System;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class cb extends ContentObserver {
    private static final Handler f2749f = new Handler();
    @Inject
    lm f2750a;
    @Inject
    bd$a f2751b;
    @Inject
    qg f2752c;
    @Inject
    Context f2753d;
    private volatile int f2754e;
    private boolean f2755g = false;

    @Inject
    cb() {
        super(f2749f);
    }

    public void m3502a() {
        this.f2754e = this.f2750a.m4339a();
        this.f2755g = true;
    }

    public void onChange(boolean selfChange) {
        try {
            super.onChange(selfChange);
            int i = this.f2754e;
            int a = this.f2750a.m4339a();
            this.f2754e = a;
            if (a != i) {
                Logger.v(Logger.DEVICE_TAG, "volume changed " + i + " --> " + a);
                this.f2752c.m4568a(this.f2751b.m3467a(i));
            }
        } catch (Throwable e) {
            Logger.e(Logger.DEVICE_TAG, e);
        }
    }

    public void m3503b() {
        if (!this.f2755g) {
            m3502a();
        }
        this.f2753d.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this);
    }

    public void m3504c() {
        this.f2753d.getContentResolver().unregisterContentObserver(this);
    }
}
