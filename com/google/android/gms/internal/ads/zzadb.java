package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.text.TextUtils;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamite.ProviderConstants;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzadb implements zzadf {
    private static final Object sLock = new Object();
    @VisibleForTesting
    private static zzadf zzcbw = null;
    @VisibleForTesting
    private static zzadf zzcbx = null;
    private final Context zzatx;
    private final Object zzcby;
    private final WeakHashMap<Thread, Boolean> zzcbz;
    private final ExecutorService zzru;
    private final zzang zzzw;

    private zzadb(Context context) {
        this(context, zzang.zzsl());
    }

    private zzadb(Context context, zzang com_google_android_gms_internal_ads_zzang) {
        this.zzcby = new Object();
        this.zzcbz = new WeakHashMap();
        this.zzru = Executors.newCachedThreadPool();
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.zzatx = context;
        this.zzzw = com_google_android_gms_internal_ads_zzang;
    }

    @VisibleForTesting
    private final Builder zza(String str, String str2, String str3, int i) {
        boolean z = false;
        try {
            z = Wrappers.packageManager(this.zzatx).isCallerInstantApp();
        } catch (Throwable th) {
            zzane.zzb("Error fetching instant app info", th);
        }
        String str4 = "unknown";
        try {
            str4 = this.zzatx.getPackageName();
        } catch (Throwable th2) {
            zzane.zzdk("Cannot obtain package name, proceeding.");
        }
        Builder appendQueryParameter = new Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("is_aia", Boolean.toString(z)).appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", VERSION.RELEASE).appendQueryParameter(ProviderConstants.API_PATH, String.valueOf(VERSION.SDK_INT));
        String str5 = "device";
        String str6 = Build.MANUFACTURER;
        String str7 = Build.MODEL;
        if (!str7.startsWith(str6)) {
            str7 = new StringBuilder((String.valueOf(str6).length() + 1) + String.valueOf(str7).length()).append(str6).append(" ").append(str7).toString();
        }
        return appendQueryParameter.appendQueryParameter(str5, str7).appendQueryParameter("js", this.zzzw.zzcw).appendQueryParameter(C1404b.f2147y, str4).appendQueryParameter("exceptiontype", str).appendQueryParameter("stacktrace", str2).appendQueryParameter("eids", TextUtils.join(",", zznk.zzjb())).appendQueryParameter("exceptionkey", str3).appendQueryParameter("cl", "193400285").appendQueryParameter("rc", "dev").appendQueryParameter("session_id", zzkb.zzih()).appendQueryParameter("sampling_rate", Integer.toString(i)).appendQueryParameter("pb_tm", String.valueOf(zzkb.zzik().zzd(zznk.zzbfo)));
    }

    public static zzadf zzc(Context context, zzang com_google_android_gms_internal_ads_zzang) {
        synchronized (sLock) {
            if (zzcbx == null) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzauh)).booleanValue()) {
                    zzadf com_google_android_gms_internal_ads_zzadb = new zzadb(context, com_google_android_gms_internal_ads_zzang);
                    Thread thread = Looper.getMainLooper().getThread();
                    if (thread != null) {
                        synchronized (com_google_android_gms_internal_ads_zzadb.zzcby) {
                            com_google_android_gms_internal_ads_zzadb.zzcbz.put(thread, Boolean.valueOf(true));
                        }
                        thread.setUncaughtExceptionHandler(new zzadd(com_google_android_gms_internal_ads_zzadb, thread.getUncaughtExceptionHandler()));
                    }
                    Thread.setDefaultUncaughtExceptionHandler(new zzadc(com_google_android_gms_internal_ads_zzadb, Thread.getDefaultUncaughtExceptionHandler()));
                    zzcbx = com_google_android_gms_internal_ads_zzadb;
                } else {
                    zzcbx = new zzadg();
                }
            }
        }
        return zzcbx;
    }

    public static zzadf zzl(Context context) {
        synchronized (sLock) {
            if (zzcbw == null) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzauh)).booleanValue()) {
                    zzcbw = new zzadb(context);
                } else {
                    zzcbw = new zzadg();
                }
            }
        }
        return zzcbw;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final void zza(java.lang.Thread r11, java.lang.Throwable r12) {
        /*
        r10 = this;
        r1 = 1;
        r3 = 0;
        if (r12 == 0) goto L_0x0049;
    L_0x0004:
        r2 = r3;
        r0 = r3;
        r5 = r12;
    L_0x0007:
        if (r5 == 0) goto L_0x003a;
    L_0x0009:
        r6 = r5.getStackTrace();
        r7 = r6.length;
        r4 = r3;
    L_0x000f:
        if (r4 >= r7) goto L_0x0034;
    L_0x0011:
        r8 = r6[r4];
        r9 = r8.getClassName();
        r9 = com.google.android.gms.internal.ads.zzamu.zzdf(r9);
        if (r9 == 0) goto L_0x001e;
    L_0x001d:
        r0 = r1;
    L_0x001e:
        r9 = r10.getClass();
        r9 = r9.getName();
        r8 = r8.getClassName();
        r8 = r9.equals(r8);
        if (r8 == 0) goto L_0x0031;
    L_0x0030:
        r2 = r1;
    L_0x0031:
        r4 = r4 + 1;
        goto L_0x000f;
    L_0x0034:
        r4 = r5.getCause();
        r5 = r4;
        goto L_0x0007;
    L_0x003a:
        if (r0 == 0) goto L_0x0049;
    L_0x003c:
        if (r2 != 0) goto L_0x0049;
    L_0x003e:
        if (r1 == 0) goto L_0x0048;
    L_0x0040:
        r0 = "";
        r1 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r10.zza(r12, r0, r1);
    L_0x0048:
        return;
    L_0x0049:
        r1 = r3;
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzadb.zza(java.lang.Thread, java.lang.Throwable):void");
    }

    public final void zza(Throwable th, String str) {
        zza(th, str, 1.0f);
    }

    public final void zza(Throwable th, String str, float f) {
        if (zzamu.zzc(th) != null) {
            String name = th.getClass().getName();
            Writer stringWriter = new StringWriter();
            zzazr.zza(th, new PrintWriter(stringWriter));
            String stringWriter2 = stringWriter.toString();
            Object obj = Math.random() < ((double) f) ? 1 : null;
            int i = f > 0.0f ? (int) (1.0f / f) : 1;
            if (obj != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(zza(name, stringWriter2, str, i).toString());
                arrayList = arrayList;
                int size = arrayList.size();
                i = 0;
                while (i < size) {
                    Object obj2 = arrayList.get(i);
                    i++;
                    this.zzru.submit(new zzade(this, new zzanf(), (String) obj2));
                }
            }
        }
    }
}
