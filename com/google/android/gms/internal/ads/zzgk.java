package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@TargetApi(14)
@ParametersAreNonnullByDefault
public final class zzgk extends Thread {
    private final Object mLock;
    private boolean mStarted = false;
    private final int zzagx;
    private final int zzagz;
    private boolean zzahy = false;
    private final zzgf zzahz;
    private final zzadf zzaia;
    private final int zzaib;
    private final int zzaic;
    private final int zzaid;
    private final int zzaie;
    private final int zzaif;
    private final int zzaig;
    private final String zzaih;
    private final boolean zzaii;
    private boolean zzbm = false;

    public zzgk(zzgf com_google_android_gms_internal_ads_zzgf, zzadf com_google_android_gms_internal_ads_zzadf) {
        this.zzahz = com_google_android_gms_internal_ads_zzgf;
        this.zzaia = com_google_android_gms_internal_ads_zzadf;
        this.mLock = new Object();
        this.zzagx = ((Integer) zzkb.zzik().zzd(zznk.zzawl)).intValue();
        this.zzaic = ((Integer) zzkb.zzik().zzd(zznk.zzawm)).intValue();
        this.zzagz = ((Integer) zzkb.zzik().zzd(zznk.zzawn)).intValue();
        this.zzaid = ((Integer) zzkb.zzik().zzd(zznk.zzawo)).intValue();
        this.zzaie = ((Integer) zzkb.zzik().zzd(zznk.zzawr)).intValue();
        this.zzaif = ((Integer) zzkb.zzik().zzd(zznk.zzawt)).intValue();
        this.zzaig = ((Integer) zzkb.zzik().zzd(zznk.zzawu)).intValue();
        this.zzaib = ((Integer) zzkb.zzik().zzd(zznk.zzawp)).intValue();
        this.zzaih = (String) zzkb.zzik().zzd(zznk.zzaww);
        this.zzaii = ((Boolean) zzkb.zzik().zzd(zznk.zzawy)).booleanValue();
        setName("ContentFetchTask");
    }

    @VisibleForTesting
    private final zzgo zza(@Nullable View view, zzge com_google_android_gms_internal_ads_zzge) {
        int i = 0;
        if (view == null) {
            return new zzgo(this, 0, 0);
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new zzgo(this, 0, 0);
            }
            com_google_android_gms_internal_ads_zzge.zzb(text.toString(), globalVisibleRect, view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
            return new zzgo(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof zzaqw)) {
            com_google_android_gms_internal_ads_zzge.zzgs();
            WebView webView = (WebView) view;
            if (PlatformVersion.isAtLeastKitKat()) {
                com_google_android_gms_internal_ads_zzge.zzgs();
                webView.post(new zzgm(this, com_google_android_gms_internal_ads_zzge, webView, globalVisibleRect));
                r0 = 1;
            } else {
                r0 = 0;
            }
            return r0 != 0 ? new zzgo(this, 0, 1) : new zzgo(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new zzgo(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i2 = 0;
            for (r0 = 0; r0 < viewGroup.getChildCount(); r0++) {
                zzgo zza = zza(viewGroup.getChildAt(r0), com_google_android_gms_internal_ads_zzge);
                i2 += zza.zzaiq;
                i += zza.zzair;
            }
            return new zzgo(this, i2, i);
        }
    }

    @VisibleForTesting
    private static boolean zzgx() {
        try {
            Context context = zzbv.zzen().getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService(C1404b.aw);
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null) {
                return false;
            }
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode()) {
                        PowerManager powerManager = (PowerManager) context.getSystemService("power");
                        if (powerManager == null ? false : powerManager.isScreenOn()) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable th) {
            zzbv.zzeo().zza(th, "ContentFetchTask.isInForeground");
            return false;
        }
    }

    private final void zzgz() {
        synchronized (this.mLock) {
            this.zzahy = true;
            zzane.zzck("ContentFetchThread: paused, mPause = " + this.zzahy);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r4 = this;
    L_0x0000:
        r0 = zzgx();	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
        if (r0 == 0) goto L_0x008f;
    L_0x0006:
        r0 = com.google.android.gms.ads.internal.zzbv.zzen();	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
        r1 = r0.getActivity();	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
        if (r1 != 0) goto L_0x0036;
    L_0x0010:
        r0 = "ContentFetchThread: no activity. Sleeping.";
        com.google.android.gms.internal.ads.zzane.zzck(r0);	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
        r4.zzgz();	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
    L_0x0019:
        r0 = r4.zzaib;	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
        r0 = r0 * 1000;
        r0 = (long) r0;	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
        java.lang.Thread.sleep(r0);	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
    L_0x0021:
        r1 = r4.mLock;
        monitor-enter(r1);
    L_0x0024:
        r0 = r4.zzahy;	 Catch:{ all -> 0x009c }
        if (r0 == 0) goto L_0x0099;
    L_0x0028:
        r0 = "ContentFetchTask: waiting";
        com.google.android.gms.internal.ads.zzane.zzck(r0);	 Catch:{ InterruptedException -> 0x0034 }
        r0 = r4.mLock;	 Catch:{ InterruptedException -> 0x0034 }
        r0.wait();	 Catch:{ InterruptedException -> 0x0034 }
        goto L_0x0024;
    L_0x0034:
        r0 = move-exception;
        goto L_0x0024;
    L_0x0036:
        if (r1 == 0) goto L_0x0019;
    L_0x0038:
        r0 = 0;
        r2 = r1.getWindow();	 Catch:{ Exception -> 0x006d, InterruptedException -> 0x0065 }
        if (r2 == 0) goto L_0x0058;
    L_0x003f:
        r2 = r1.getWindow();	 Catch:{ Exception -> 0x006d, InterruptedException -> 0x0065 }
        r2 = r2.getDecorView();	 Catch:{ Exception -> 0x006d, InterruptedException -> 0x0065 }
        if (r2 == 0) goto L_0x0058;
    L_0x0049:
        r1 = r1.getWindow();	 Catch:{ Exception -> 0x006d, InterruptedException -> 0x0065 }
        r1 = r1.getDecorView();	 Catch:{ Exception -> 0x006d, InterruptedException -> 0x0065 }
        r2 = 16908290; // 0x1020002 float:2.3877235E-38 double:8.353805E-317;
        r0 = r1.findViewById(r2);	 Catch:{ Exception -> 0x006d, InterruptedException -> 0x0065 }
    L_0x0058:
        if (r0 == 0) goto L_0x0019;
    L_0x005a:
        if (r0 == 0) goto L_0x0019;
    L_0x005c:
        r1 = new com.google.android.gms.internal.ads.zzgl;	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
        r1.<init>(r4, r0);	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
        r0.post(r1);	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
        goto L_0x0019;
    L_0x0065:
        r0 = move-exception;
        r1 = "Error in ContentFetchTask";
        com.google.android.gms.internal.ads.zzane.zzb(r1, r0);
        goto L_0x0021;
    L_0x006d:
        r1 = move-exception;
        r2 = com.google.android.gms.ads.internal.zzbv.zzeo();	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
        r3 = "ContentFetchTask.extractContent";
        r2.zza(r1, r3);	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
        r1 = "Failed getting root view of activity. Content not extracted.";
        com.google.android.gms.internal.ads.zzane.zzck(r1);	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
        goto L_0x0058;
    L_0x007f:
        r0 = move-exception;
        r1 = "Error in ContentFetchTask";
        com.google.android.gms.internal.ads.zzane.zzb(r1, r0);
        r1 = r4.zzaia;
        r2 = "ContentFetchTask.run";
        r1.zza(r0, r2);
        goto L_0x0021;
    L_0x008f:
        r0 = "ContentFetchTask: sleeping";
        com.google.android.gms.internal.ads.zzane.zzck(r0);	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
        r4.zzgz();	 Catch:{ InterruptedException -> 0x0065, Exception -> 0x007f }
        goto L_0x0019;
    L_0x0099:
        monitor-exit(r1);	 Catch:{ all -> 0x009c }
        goto L_0x0000;
    L_0x009c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x009c }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgk.run():void");
    }

    public final void wakeup() {
        synchronized (this.mLock) {
            this.zzahy = false;
            this.mLock.notifyAll();
            zzane.zzck("ContentFetchThread: wakeup");
        }
    }

    @VisibleForTesting
    final void zza(zzge com_google_android_gms_internal_ads_zzge, WebView webView, String str, boolean z) {
        com_google_android_gms_internal_ads_zzge.zzgr();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString("text");
                if (this.zzaii || TextUtils.isEmpty(webView.getTitle())) {
                    com_google_android_gms_internal_ads_zzge.zza(optString, z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                } else {
                    String title = webView.getTitle();
                    com_google_android_gms_internal_ads_zzge.zza(new StringBuilder((String.valueOf(title).length() + 1) + String.valueOf(optString).length()).append(title).append("\n").append(optString).toString(), z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                }
            }
            if (com_google_android_gms_internal_ads_zzge.zzgn()) {
                this.zzahz.zzb(com_google_android_gms_internal_ads_zzge);
            }
        } catch (JSONException e) {
            zzane.zzck("Json string may be malformed.");
        } catch (Throwable th) {
            zzane.zza("Failed to get webview content.", th);
            this.zzaia.zza(th, "ContentFetchTask.processWebViewContent");
        }
    }

    public final void zzgw() {
        synchronized (this.mLock) {
            if (this.mStarted) {
                zzane.zzck("Content hash thread already started, quiting...");
                return;
            }
            this.mStarted = true;
            start();
        }
    }

    public final zzge zzgy() {
        return this.zzahz.zzgv();
    }

    public final boolean zzha() {
        return this.zzahy;
    }

    @VisibleForTesting
    final void zzk(View view) {
        try {
            zzge com_google_android_gms_internal_ads_zzge = new zzge(this.zzagx, this.zzaic, this.zzagz, this.zzaid, this.zzaie, this.zzaif, this.zzaig);
            Context context = zzbv.zzen().getContext();
            if (!(context == null || TextUtils.isEmpty(this.zzaih))) {
                String str = (String) view.getTag(context.getResources().getIdentifier((String) zzkb.zzik().zzd(zznk.zzawv), "id", context.getPackageName()));
                if (str != null && str.equals(this.zzaih)) {
                    return;
                }
            }
            zzgo zza = zza(view, com_google_android_gms_internal_ads_zzge);
            com_google_android_gms_internal_ads_zzge.zzgt();
            if (zza.zzaiq != 0 || zza.zzair != 0) {
                if (zza.zzair != 0 || com_google_android_gms_internal_ads_zzge.zzgu() != 0) {
                    if (zza.zzair != 0 || !this.zzahz.zza(com_google_android_gms_internal_ads_zzge)) {
                        this.zzahz.zzc(com_google_android_gms_internal_ads_zzge);
                    }
                }
            }
        } catch (Throwable e) {
            zzane.zzb("Exception in fetchContentOnUIThread", e);
            this.zzaia.zza(e, "ContentFetchTask.fetchContent");
        }
    }
}
