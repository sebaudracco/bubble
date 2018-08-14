package com.fyber.requesters.p097a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.fyber.utils.FyberLogger;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/* compiled from: RewardedVideoCustomizer */
public final class C2635n implements C2620d {
    private final boolean f6548a;

    public C2635n(Context context) {
        this.f6548a = C2635n.m8440a(context);
    }

    private static boolean m8440a(Context context) {
        Exception e;
        boolean z = true;
        if (context != null) {
            try {
                if (context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean("FYBEnableSSLRewardedVideo", true)) {
                    z = false;
                }
                if (!z) {
                    return z;
                }
                FyberLogger.m8448d("RewardedVideoCustomizer", "Manifest metadata - disabling SSL");
                return z;
            } catch (NameNotFoundException e2) {
                e = e2;
                FyberLogger.m8449e("RewardedVideoCustomizer", "Failed to load meta-data from Manifest: " + e.getMessage());
                return false;
            } catch (NullPointerException e3) {
                e = e3;
                FyberLogger.m8449e("RewardedVideoCustomizer", "Failed to load meta-data from Manifest: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public final void mo4002a(C2623c c2623c, C2634m c2634m) {
        Calendar gregorianCalendar = new GregorianCalendar();
        c2634m.m8437c().m8539a("timezone_offset_in_seconds", String.valueOf(TimeUnit.SECONDS.convert((long) gregorianCalendar.getTimeZone().getOffset(gregorianCalendar.getTimeInMillis()), TimeUnit.MILLISECONDS))).m8539a("ad_format", "video");
        if (this.f6548a) {
            FyberLogger.m8448d("RewardedVideoCustomizer", "Manifest metadata - disabling SSL");
            c2634m.m8437c().m8546d();
        }
        c2623c.m8402a("TRACKING_URL_KEY", (Object) "rewarded_video_tracking");
    }
}
