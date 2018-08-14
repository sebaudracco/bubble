package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build.VERSION;
import com.google.android.gms.ads.internal.zzbv;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@zzadh
public final class zznm {
    private Context mContext = null;
    private String zzaej = null;
    private String zzbfx;
    private Map<String, String> zzbfy;

    public zznm(Context context, String str) {
        this.mContext = context;
        this.zzaej = str;
        this.zzbfx = (String) zzkb.zzik().zzd(zznk.zzawi);
        this.zzbfy = new LinkedHashMap();
        this.zzbfy.put("s", "gmob_sdk");
        this.zzbfy.put("v", "3");
        this.zzbfy.put("os", VERSION.RELEASE);
        this.zzbfy.put("sdk", VERSION.SDK);
        zzbv.zzek();
        this.zzbfy.put("device", zzakk.zzri());
        this.zzbfy.put("app", context.getApplicationContext() != null ? context.getApplicationContext().getPackageName() : context.getPackageName());
        Map map = this.zzbfy;
        String str2 = "is_lite_sdk";
        zzbv.zzek();
        map.put(str2, zzakk.zzav(context) ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
        Future zzq = zzbv.zzev().zzq(this.mContext);
        try {
            zzq.get();
            this.zzbfy.put("network_coarse", Integer.toString(((zzaga) zzq.get()).zzcjx));
            this.zzbfy.put("network_fine", Integer.toString(((zzaga) zzq.get()).zzcjy));
        } catch (Throwable e) {
            zzbv.zzeo().zza(e, "CsiConfiguration.CsiConfiguration");
        }
    }

    final Context getContext() {
        return this.mContext;
    }

    final String zzfw() {
        return this.zzaej;
    }

    final String zzjd() {
        return this.zzbfx;
    }

    final Map<String, String> zzje() {
        return this.zzbfy;
    }
}
