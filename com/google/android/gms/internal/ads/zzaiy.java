package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement$Event;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzaiy {
    private final AtomicReference<ThreadPoolExecutor> zzcnp = new AtomicReference(null);
    private final Object zzcnq = new Object();
    @Nullable
    @GuardedBy("mGmpAppIdLock")
    private String zzcnr = null;
    @VisibleForTesting
    private final AtomicBoolean zzcns = new AtomicBoolean(false);
    @VisibleForTesting
    private final AtomicInteger zzcnt = new AtomicInteger(-1);
    private final AtomicReference<Object> zzcnu = new AtomicReference(null);
    private final AtomicReference<Object> zzcnv = new AtomicReference(null);
    private ConcurrentMap<String, Method> zzcnw = new ConcurrentHashMap(9);

    private static Bundle zza(Context context, String str, boolean z) {
        Throwable e;
        String str2;
        String valueOf;
        Bundle bundle = new Bundle();
        try {
            bundle.putLong("_aeid", Long.parseLong(str));
        } catch (NullPointerException e2) {
            e = e2;
            str2 = "Invalid event ID: ";
            valueOf = String.valueOf(str);
            zzane.zzb(valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2), e);
            if (z) {
                bundle.putInt("_r", 1);
            }
            return bundle;
        } catch (NumberFormatException e3) {
            e = e3;
            str2 = "Invalid event ID: ";
            valueOf = String.valueOf(str);
            if (valueOf.length() == 0) {
            }
            zzane.zzb(valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2), e);
            if (z) {
                bundle.putInt("_r", 1);
            }
            return bundle;
        }
        if (z) {
            bundle.putInt("_r", 1);
        }
        return bundle;
    }

    private final Object zza(String str, Context context) {
        Object obj = null;
        if (zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzcnu, true)) {
            try {
                obj = zzi(context, str).invoke(this.zzcnu.get(), new Object[0]);
            } catch (Exception e) {
                zza(e, str, true);
            }
        }
        return obj;
    }

    private final void zza(Context context, String str, Bundle bundle) {
        if (zzs(context) && zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzcnu, true)) {
            Method zzac = zzac(context);
            try {
                zzac.invoke(this.zzcnu.get(), new Object[]{"am", str, bundle});
            } catch (Exception e) {
                zza(e, "logEventInternal", true);
            }
        }
    }

    private final void zza(Exception exception, String str, boolean z) {
        if (!this.zzcns.get()) {
            zzane.zzdk(new StringBuilder(String.valueOf(str).length() + 30).append("Invoke Firebase method ").append(str).append(" error.").toString());
            if (z) {
                zzane.zzdk("The Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires the latest Firebase SDK jar, but Firebase SDK is either missing or out of date");
                this.zzcns.set(true);
            }
        }
    }

    private final boolean zza(Context context, String str, AtomicReference<Object> atomicReference, boolean z) {
        if (atomicReference.get() == null) {
            try {
                atomicReference.compareAndSet(null, context.getClassLoader().loadClass(str).getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{context}));
            } catch (Exception e) {
                zza(e, "getInstance", z);
                return false;
            }
        }
        return true;
    }

    private final Method zzac(Context context) {
        Method method = (Method) this.zzcnw.get("logEventInternal");
        if (method != null) {
            return method;
        }
        try {
            method = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod("logEventInternal", new Class[]{String.class, String.class, Bundle.class});
            this.zzcnw.put("logEventInternal", method);
            return method;
        } catch (Exception e) {
            zza(e, "logEventInternal", true);
            return null;
        }
    }

    private final void zzb(Context context, String str, String str2) {
        if (zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzcnu, true)) {
            Method zzh = zzh(context, str2);
            try {
                zzh.invoke(this.zzcnu.get(), new Object[]{str});
                zzakb.m3428v(new StringBuilder((String.valueOf(str2).length() + 37) + String.valueOf(str).length()).append("Invoke Firebase method ").append(str2).append(", Ad Unit Id: ").append(str).toString());
            } catch (Exception e) {
                zza(e, str2, false);
            }
        }
    }

    private final Method zzh(Context context, String str) {
        Method method = (Method) this.zzcnw.get(str);
        if (method != null) {
            return method;
        }
        try {
            method = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, new Class[]{String.class});
            this.zzcnw.put(str, method);
            return method;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final Method zzi(Context context, String str) {
        Method method = (Method) this.zzcnw.get(str);
        if (method != null) {
            return method;
        }
        try {
            method = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, new Class[0]);
            this.zzcnw.put(str, method);
            return method;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final Method zzj(Context context, String str) {
        Method method = (Method) this.zzcnw.get(str);
        if (method != null) {
            return method;
        }
        try {
            method = context.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics").getDeclaredMethod(str, new Class[]{Activity.class, String.class, String.class});
            this.zzcnw.put(str, method);
            return method;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    public final void zza(Context context, String str, String str2) {
        if (zzs(context)) {
            zza(context, str, zza(context, str2, "_ac".equals(str)));
        }
    }

    public final void zza(Context context, String str, String str2, String str3, int i) {
        if (zzs(context)) {
            Bundle zza = zza(context, str, false);
            zza.putString("_ai", str2);
            zza.putString("type", str3);
            zza.putInt(FirebaseAnalytics$Param.VALUE, i);
            zza(context, AppMeasurement$Event.AD_REWARD, zza);
            zzakb.m3428v(new StringBuilder(String.valueOf(str3).length() + 75).append("Log a Firebase reward video event, reward type: ").append(str3).append(", reward value: ").append(i).toString());
        }
    }

    @Nullable
    public final String zzaa(Context context) {
        if (!zzs(context)) {
            return null;
        }
        long longValue = ((Long) zzkb.zzik().zzd(zznk.zzaxt)).longValue();
        if (longValue < 0) {
            return (String) zza("getAppInstanceId", context);
        }
        if (this.zzcnp.get() == null) {
            this.zzcnp.compareAndSet(null, new ThreadPoolExecutor(((Integer) zzkb.zzik().zzd(zznk.zzaxu)).intValue(), ((Integer) zzkb.zzik().zzd(zznk.zzaxu)).intValue(), 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), new zzaja(this)));
        }
        Future submit = ((ThreadPoolExecutor) this.zzcnp.get()).submit(new zzaiz(this, context));
        try {
            return (String) submit.get(longValue, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            submit.cancel(true);
            return e instanceof TimeoutException ? "TIME_OUT" : null;
        }
    }

    @Nullable
    public final String zzab(Context context) {
        if (!zzs(context)) {
            return null;
        }
        Object zza = zza("generateEventId", context);
        return zza != null ? zza.toString() : null;
    }

    final /* synthetic */ String zzad(Context context) throws Exception {
        return (String) zza("getAppInstanceId", context);
    }

    public final void zzb(Context context, String str) {
        if (zzs(context)) {
            zzb(context, str, "beginAdUnitExposure");
        }
    }

    public final void zzc(Context context, String str) {
        if (zzs(context)) {
            zzb(context, str, "endAdUnitExposure");
        }
    }

    public final void zzd(Context context, String str) {
        if (zzs(context) && (context instanceof Activity) && zza(context, "com.google.firebase.analytics.FirebaseAnalytics", this.zzcnv, false)) {
            Method zzj = zzj(context, "setCurrentScreen");
            try {
                Activity activity = (Activity) context;
                zzj.invoke(this.zzcnv.get(), new Object[]{activity, str, context.getPackageName()});
            } catch (Exception e) {
                zza(e, "setCurrentScreen", false);
            }
        }
    }

    public final void zze(Context context, String str) {
        zza(context, "_ac", str);
    }

    public final void zzf(Context context, String str) {
        zza(context, "_ai", str);
    }

    public final void zzg(Context context, String str) {
        zza(context, "_aq", str);
    }

    public final boolean zzs(Context context) {
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzaxj)).booleanValue() || this.zzcns.get()) {
            return false;
        }
        if (this.zzcnt.get() == -1) {
            zzkb.zzif();
            if (!zzamu.zzbe(context)) {
                zzkb.zzif();
                if (zzamu.zzbh(context)) {
                    zzane.zzdk("Google Play Service is out of date, the Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires updated Google Play Service.");
                    this.zzcnt.set(0);
                }
            }
            this.zzcnt.set(1);
        }
        return this.zzcnt.get() == 1;
    }

    public final boolean zzt(Context context) {
        return ((Boolean) zzkb.zzik().zzd(zznk.zzaxk)).booleanValue() && zzs(context);
    }

    public final boolean zzu(Context context) {
        return ((Boolean) zzkb.zzik().zzd(zznk.zzaxl)).booleanValue() && zzs(context);
    }

    public final boolean zzv(Context context) {
        return ((Boolean) zzkb.zzik().zzd(zznk.zzaxm)).booleanValue() && zzs(context);
    }

    public final boolean zzw(Context context) {
        return ((Boolean) zzkb.zzik().zzd(zznk.zzaxn)).booleanValue() && zzs(context);
    }

    public final boolean zzx(Context context) {
        return ((Boolean) zzkb.zzik().zzd(zznk.zzaxq)).booleanValue() && zzs(context);
    }

    public final String zzy(Context context) {
        if (!zzs(context)) {
            return "";
        }
        if (!zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzcnu, true)) {
            return "";
        }
        try {
            String str = (String) zzi(context, "getCurrentScreenName").invoke(this.zzcnu.get(), new Object[0]);
            if (str == null) {
                str = (String) zzi(context, "getCurrentScreenClass").invoke(this.zzcnu.get(), new Object[0]);
            }
            return str == null ? "" : str;
        } catch (Exception e) {
            zza(e, "getCurrentScreenName", false);
            return "";
        }
    }

    @Nullable
    public final String zzz(Context context) {
        if (!zzs(context)) {
            return null;
        }
        synchronized (this.zzcnq) {
            if (this.zzcnr != null) {
                String str = this.zzcnr;
                return str;
            }
            this.zzcnr = (String) zza("getGmpAppId", context);
            str = this.zzcnr;
            return str;
        }
    }
}
