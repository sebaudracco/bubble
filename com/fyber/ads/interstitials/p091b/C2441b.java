package com.fyber.ads.interstitials.p091b;

import android.app.Activity;
import android.content.Intent;
import com.fyber.ads.internal.C2425d;
import com.fyber.ads.interstitials.InterstitialActivity;
import com.fyber.ads.interstitials.InterstitialAd;
import com.fyber.utils.FyberLogger;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: InterstitialClient */
public final class C2441b {
    private static final C2441b f6109a = new C2441b();
    private AtomicReference<C2425d> f6110b = new AtomicReference(C2425d.READY_TO_CHECK_OFFERS);
    private C2440a f6111c;

    public static boolean m7759a(C2425d c2425d) {
        f6109a.f6110b.getAndSet(c2425d);
        return true;
    }

    public static C2425d m7756a() {
        return (C2425d) f6109a.f6110b.get();
    }

    public static void m7758a(C2440a c2440a) {
        f6109a.f6111c = c2440a;
    }

    public static void m7757a(InterstitialAd interstitialAd, Activity activity) {
        if (!C2441b.m7760a(interstitialAd)) {
            if (f6109a.f6111c != null) {
                f6109a.f6111c.m7749a("It is not possible to show Interstitials at this moment");
            } else {
                FyberLogger.m8453w("InterstitialClient", "There was an issue with a missing offer");
            }
        }
        Intent intent = new Intent(activity, InterstitialActivity.class);
        intent.setFlags(ErrorDialogData.BINDER_CRASH);
        activity.getApplicationContext().startActivity(intent);
    }

    public static boolean m7760a(InterstitialAd interstitialAd) {
        return f6109a.f6111c != null && ((InterstitialAd) f6109a.f6111c.m7626i()).equals(interstitialAd);
    }

    public static C2440a m7761b() {
        return f6109a.f6111c;
    }
}
