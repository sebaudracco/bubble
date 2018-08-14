package com.vungle.publisher.env;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.vungle.publisher.bw;
import com.vungle.publisher.bw.b;
import com.vungle.publisher.env.AndroidDevice.DeviceIdStrategy;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.pf;
import com.vungle.publisher.qg;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class C1609d extends DeviceIdStrategy {
    @Inject
    protected Context f2895c;
    @Inject
    protected qg f2896d;
    @Inject
    protected bw f2897e;

    @Inject
    C1609d() {
    }

    protected void m3897d(AndroidDevice androidDevice) {
        this.f2897e.m3474a(C1611e.m3903a(this, androidDevice), b.a);
    }

    /* synthetic */ void m3899f(AndroidDevice androidDevice) {
        m3898e(androidDevice);
    }

    protected void m3898e(AndroidDevice androidDevice) {
        Object obj = !mo2986a(androidDevice) ? 1 : null;
        if (mo2987b(androidDevice) && obj != null) {
            this.f2896d.m4568a(new pf());
        }
    }

    protected boolean mo2986a(AndroidDevice androidDevice) {
        return androidDevice.m3874b();
    }

    protected boolean mo2987b(AndroidDevice androidDevice) {
        String string;
        Throwable e;
        boolean z = true;
        boolean z2 = false;
        if (w.a) {
            try {
                ContentResolver contentResolver = this.f2895c.getContentResolver();
                if (Secure.getInt(contentResolver, "limit_ad_tracking") != 1) {
                    z = z2;
                }
                try {
                    string = Secure.getString(contentResolver, C1404b.f2113Q);
                } catch (SettingNotFoundException e2) {
                    e = e2;
                    try {
                        Logger.w(Logger.DEVICE_TAG, "Error getting Amazon advertising info", e);
                        string = null;
                        androidDevice.m3871a(z);
                        androidDevice.m3870a(string);
                    } catch (Throwable e3) {
                        Logger.w(Logger.DEVICE_TAG, "error fetching advertising ID and ad tracking preference", e3);
                    }
                    z2 = androidDevice.m3874b();
                    return z2;
                }
            } catch (SettingNotFoundException e4) {
                e3 = e4;
                z = z2;
                Logger.w(Logger.DEVICE_TAG, "Error getting Amazon advertising info", e3);
                string = null;
                androidDevice.m3871a(z);
                androidDevice.m3870a(string);
                z2 = androidDevice.m3874b();
                return z2;
            }
        }
        if (androidDevice.m3886m()) {
            Logger.d(Logger.DEVICE_TAG, "fetching advertising ID and ad tracking preference");
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.f2895c);
            if (advertisingIdInfo != null) {
                string = advertisingIdInfo.getId();
                z = advertisingIdInfo.isLimitAdTrackingEnabled();
                Logger.d(Logger.DEVICE_TAG, "advertising ID " + string + "; ad tracking enabled " + z);
            }
        }
        z = z2;
        string = null;
        androidDevice.m3871a(z);
        if (!(string == null || z)) {
            androidDevice.m3870a(string);
        }
        try {
            z2 = androidDevice.m3874b();
        } catch (Throwable e32) {
            Logger.w(Logger.DEVICE_TAG, "error verifying advertising ID", e32);
        }
        return z2;
    }
}
