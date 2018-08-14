package com.google.android.gms.ads.internal;

import android.os.AsyncTask;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class zzbt extends AsyncTask<Void, Void, String> {
    private final /* synthetic */ zzbp zzaba;

    private zzbt(zzbp com_google_android_gms_ads_internal_zzbp) {
        this.zzaba = com_google_android_gms_ads_internal_zzbp;
    }

    private final String zza(Void... voidArr) {
        Throwable e;
        try {
            zzbp.zza(this.zzaba, (zzci) zzbp.zze(this.zzaba).get(((Long) zzkb.zzik().zzd(zznk.zzbdb)).longValue(), TimeUnit.MILLISECONDS));
        } catch (InterruptedException e2) {
            e = e2;
            zzane.zzc("", e);
            return this.zzaba.zzea();
        } catch (ExecutionException e3) {
            e = e3;
            zzane.zzc("", e);
            return this.zzaba.zzea();
        } catch (TimeoutException e4) {
            e = e4;
            zzane.zzc("", e);
            return this.zzaba.zzea();
        }
        return this.zzaba.zzea();
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return zza((Void[]) objArr);
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        if (zzbp.zzf(this.zzaba) != null && str != null) {
            zzbp.zzf(this.zzaba).loadUrl(str);
        }
    }
}
