package com.vungle.publisher.env;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class C1610a extends C1609d {
    @Inject
    Context f2898a;
    @Inject
    WifiManager f2899b;

    @Inject
    C1610a() {
    }

    protected boolean mo2986a(AndroidDevice androidDevice) {
        return androidDevice.m3882i();
    }

    protected boolean mo2987b(AndroidDevice androidDevice) {
        try {
            boolean b = super.mo2987b(androidDevice);
            if (b) {
                Logger.v(Logger.DEVICE_TAG, "have advertising ID - not fetching fallback device IDs");
                return b;
            }
            Logger.d(Logger.DEVICE_TAG, "ensuring fallback device IDs");
            m3902c(androidDevice);
            return true;
        } catch (Throwable e) {
            Logger.w(Logger.DEVICE_TAG, e);
            return androidDevice.m3882i();
        }
    }

    protected void m3902c(AndroidDevice androidDevice) {
        if (androidDevice.m3879f()) {
            Logger.v(Logger.DEVICE_TAG, "existing android ID " + androidDevice.m3877d());
            return;
        }
        String string = Secure.getString(this.f2898a.getContentResolver(), "android_id");
        Logger.d(Logger.DEVICE_TAG, "fetched android ID " + string);
        androidDevice.m3873b(string);
    }
}
