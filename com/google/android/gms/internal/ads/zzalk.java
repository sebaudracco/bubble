package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONObject;

@zzadh
public final class zzalk {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private String zzcsm = "";
    @GuardedBy("mLock")
    private String zzcsn = "";
    @GuardedBy("mLock")
    private boolean zzcso = false;
    @VisibleForTesting
    private String zzcsp = "";

    @VisibleForTesting
    private final void zza(Context context, String str, boolean z, boolean z2) {
        if (context instanceof Activity) {
            zzakk.zzcrm.post(new zzall(this, context, str, z, z2));
        } else {
            zzane.zzdj("Can not create dialog without Activity Context");
        }
    }

    private final String zzaz(Context context) {
        String str;
        synchronized (this.mLock) {
            if (TextUtils.isEmpty(this.zzcsm)) {
                zzbv.zzek();
                this.zzcsm = zzakk.zzn(context, "debug_signals_id.txt");
                if (TextUtils.isEmpty(this.zzcsm)) {
                    zzbv.zzek();
                    this.zzcsm = zzakk.zzrh();
                    zzbv.zzek();
                    zzakk.zze(context, "debug_signals_id.txt", this.zzcsm);
                }
            }
            str = this.zzcsm;
        }
        return str;
    }

    private final Uri zzc(Context context, String str, String str2, String str3) {
        Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("linkedDeviceId", zzaz(context));
        buildUpon.appendQueryParameter("adSlotPath", str2);
        buildUpon.appendQueryParameter("afmaVersion", str3);
        return buildUpon.build();
    }

    @VisibleForTesting
    private final boolean zzh(Context context, String str, String str2) {
        Object zzj = zzj(context, zzc(context, (String) zzkb.zzik().zzd(zznk.zzbeg), str, str2).toString(), str2);
        if (TextUtils.isEmpty(zzj)) {
            zzane.zzck("Not linked for in app preview.");
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(zzj.trim());
            String optString = jSONObject.optString("gct");
            this.zzcsp = jSONObject.optString("status");
            synchronized (this.mLock) {
                this.zzcsn = optString;
            }
            return true;
        } catch (Throwable e) {
            zzane.zzc("Fail to get in app preview response json.", e);
            return false;
        }
    }

    @VisibleForTesting
    private final boolean zzi(Context context, String str, String str2) {
        Object zzj = zzj(context, zzc(context, (String) zzkb.zzik().zzd(zznk.zzbeh), str, str2).toString(), str2);
        if (TextUtils.isEmpty(zzj)) {
            zzane.zzck("Not linked for debug signals.");
            return false;
        }
        try {
            boolean equals = SchemaSymbols.ATTVAL_TRUE_1.equals(new JSONObject(zzj.trim()).optString("debug_mode"));
            synchronized (this.mLock) {
                this.zzcso = equals;
            }
            return equals;
        } catch (Throwable e) {
            zzane.zzc("Fail to get debug mode response json.", e);
            return false;
        }
    }

    @VisibleForTesting
    private static String zzj(Context context, String str, String str2) {
        Throwable th;
        String str3;
        String valueOf;
        Map hashMap = new HashMap();
        hashMap.put("User-Agent", zzbv.zzek().zzm(context, str2));
        zzanz zzc = new zzalt(context).zzc(str, hashMap);
        try {
            return (String) zzc.get((long) ((Integer) zzkb.zzik().zzd(zznk.zzbej)).intValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            th = e;
            str3 = "Timeout while retriving a response from: ";
            valueOf = String.valueOf(str);
            zzane.zzb(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), th);
            zzc.cancel(true);
        } catch (Throwable e2) {
            th = e2;
            str3 = "Interrupted while retriving a response from: ";
            valueOf = String.valueOf(str);
            zzane.zzb(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), th);
            zzc.cancel(true);
        } catch (Throwable e22) {
            th = e22;
            String str4 = "Error retriving a response from: ";
            valueOf = String.valueOf(str);
            zzane.zzb(valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4), th);
        }
        return null;
    }

    private final void zzk(Context context, String str, String str2) {
        zzbv.zzek();
        zzakk.zza(context, zzc(context, (String) zzkb.zzik().zzd(zznk.zzbef), str, str2));
    }

    public final void zza(Context context, String str, String str2, @Nullable String str3) {
        boolean zzrx = zzrx();
        if (zzi(context, str, str2)) {
            if (!(zzrx || TextUtils.isEmpty(str3))) {
                zzb(context, str2, str3, str);
            }
            zzane.zzck("Device is linked for debug signals.");
            zza(context, "The device is successfully linked for troubleshooting.", false, true);
            return;
        }
        zzk(context, str, str2);
    }

    public final void zzb(Context context, String str, String str2, String str3) {
        Builder buildUpon = zzc(context, (String) zzkb.zzik().zzd(zznk.zzbei), str3, str).buildUpon();
        buildUpon.appendQueryParameter("debugData", str2);
        zzbv.zzek();
        zzakk.zzd(context, str, buildUpon.build().toString());
    }

    public final void zzg(Context context, String str, String str2) {
        if (!zzh(context, str, str2)) {
            zza(context, "In-app preview failed to load because of a system error. Please try again later.", true, true);
        } else if ("2".equals(this.zzcsp)) {
            zzane.zzck("Creative is not pushed for this device.");
            zza(context, "There was no creative pushed from DFP to the device.", false, false);
        } else if (SchemaSymbols.ATTVAL_TRUE_1.equals(this.zzcsp)) {
            zzane.zzck("The app is not linked for creative preview.");
            zzk(context, str, str2);
        } else if (SchemaSymbols.ATTVAL_FALSE_0.equals(this.zzcsp)) {
            zzane.zzck("Device is linked for in app preview.");
            zza(context, "The device is successfully linked for creative preview.", false, true);
        }
    }

    public final String zzrw() {
        String str;
        synchronized (this.mLock) {
            str = this.zzcsn;
        }
        return str;
    }

    public final boolean zzrx() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcso;
        }
        return z;
    }
}
